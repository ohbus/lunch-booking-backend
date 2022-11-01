/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.*;
import xyz.subho.lunchbooking.exceptions.BookingNotFoundException;
import xyz.subho.lunchbooking.exceptions.InvalidBookingOperation;
import xyz.subho.lunchbooking.exceptions.SelectionLockedException;
import xyz.subho.lunchbooking.exceptions.SelectionNotAvailableException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.repositories.AvailableBookingsRepository;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.BookingService;
import xyz.subho.lunchbooking.services.MealsService;
import xyz.subho.lunchbooking.services.UserService;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

  @Autowired private BookingRepository bookingRepository;

  @Autowired private AvailableBookingsRepository availableBookingsRepository;

  @Autowired private MealsRepository mealsRepository;

  @Autowired private MealsService mealsService;

  @Autowired private UserService userService;

  @Autowired
  @Qualifier("BookingResponseMapper")
  private Mapper<Bookings, BookingResponseModel> bookingResponseModelMapper;

  @Override
  @Transactional
  public long createBooking(long mealOptionId, long userId) {
    log.debug(
        "Creating a New Booking for User ID:{} against Meal Option ID:{}", userId, mealOptionId);
    var user = userService.getUserById(userId);
    var mealOption = mealsService.getMealOptionById(mealOptionId);
    var meal = mealOption.getMeals();
    checkMealValidity(meal);
    var date = meal.getDate();
    var bookingOpt = bookingRepository.findByDateAndUser_IdAndCancelledAtNull(date, userId);
    if (bookingOpt.isPresent()) {
      log.warn("Booking Already Exists for User ID:{} on Date:{}", userId, date);
      var existingBooking = bookingOpt.get();
      log.debug(
          "Attempting to Cancel Previous Booking with ID:{} for UserID:{} on {}",
          existingBooking.getId(),
          userId,
          date);
      existingBooking.cancelBooking();
      mealsService
          .getMealOptionsByBookingId(existingBooking.getId())
          .removeBooking(existingBooking);
    }
    var booking = new Bookings().withUser(user).withDate(date).withMealOptions(mealOption);
    log.debug("Creating Booking for UserID:{} and Meal Options ID:{}", userId, mealOptionId);
    booking = bookingRepository.save(booking);
    mealOption.addBooking(booking);
    log.debug("Booking ID:{} Created", booking.getId());
    return booking.getId();
  }

  private void checkMealValidity(Meals meal) {
    log.debug("Checking Meal ID:{} for Validity", meal.getId());
    if (meal.isLocked()) {
      log.error("Booking cannot be created as Meal ID:{} is already Locked", meal.getId());
      throw new SelectionLockedException(
          String.format("Meal:%s is already LOCKED for Booking!", meal.getName()));
    }
    if (!meal.isActivated()) {
      log.error("Meal:{} is not Activated, so it not available for Booking!", meal.getId());
      throw new SelectionNotAvailableException(
          String.format("Selection:%s is NOT Available for Booking.", meal.getName()));
    }
    log.debug("Meal ID:{} is VALID", meal.getId());
  }

  @Override
  @Transactional
  public long cancelBookingById(long id, long userId) {
    log.debug("Trying to Cancel Booking ID:{} by User ID:{}", id, userId);
    var booking = getBookingById(id);
    var user = userService.getUserById(userId);
    if (Objects.nonNull(booking.getCancelledAt())) {
      log.error("Booking ID:{} is already cancelled", id);
      throw new InvalidBookingOperation(String.format("Booking ID:%s is already cancelled!", id));
    }
    checkIfBookingOwnedByUser(booking, user);
    var mealOption = mealsService.getMealOptionsByBookingId(id);
    mealOption.removeBookingById(id);

    Optional<Long> lockedAt = Optional.ofNullable(mealOption.getMeals().getLockedAt());
    // If the Booking is Cancelled after Locking the Meal
    if (lockedAt.isPresent()
        && LocalDateTime.now()
            .isAfter(
                LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(lockedAt.get()), ZoneId.systemDefault()))) {
      // Available Meal Bookings for a particular day is created for broadcast
      createAvailableMealForToday(mealOption);
    }
    log.debug("Booking ID:{} is being CANCELLED", id);
    return booking.cancelBooking();
  }

  @Transactional
  protected void createAvailableMealForToday(MealOptions mealOptions) {
    log.debug("Meal Option:{} is being made available for Booking", mealOptions.getId());
    var date = mealOptions.getMeals().getDate();
    var availableBookingOpt =
        availableBookingsRepository.findByDateAndMealOptions_Id(date, mealOptions.getId());
    if (availableBookingOpt.isPresent()) {
      log.debug(
          "Available Meal Option is already present, thus adding one more to Avail ID:{}",
          availableBookingOpt.get().getId());
      // Adds another Meal which got cancelled
      availableBookingOpt.get().add();
    } else {
      // If there is no available meal option, then creates one
      var id = availableBookingsRepository.save(new AvailableBookings(date, mealOptions)).getId();
      log.debug(
          "No new meal options were present for Meal Option:{} Created NEW Avail ID:{}",
          mealOptions.getId(),
          id);
    }
    log.debug("One more meal has been made available for Meal Option ID:{}", mealOptions.getId());
  }

  @Override
  @Transactional
  public long claimAvailableMeal(long mealOptionId, long userId) {
    log.debug("Trying to claim available Meal Option ID:{} for User ID:{}", mealOptionId, userId);
    var today = LocalDate.now();
    // Checks if there is an existing booking for today
    if (bookingRepository.existsByDateAndUser_IdAndCancelledAtNull(today, userId)) {
      log.error("Booking Already Exists for User ID:{} on Date:{}", userId, today);
      throw new InvalidBookingOperation(
          String.format("Booking Already Exists for User ID:%s for Today:%s", userId, today));
    }
    // finds if there are any available meals
    var availableBookingOpt =
        availableBookingsRepository.findByDateAndMealOptions_Id(today, mealOptionId);
    if (availableBookingOpt.isPresent()) {
      var availableBooking = availableBookingOpt.get();
      if (availableBooking.isAvailable()) {
        log.debug("Meal Option:{} is available for booking by User ID:{}", mealOptionId, userId);
        var mealOption = mealsService.getMealOptionById(mealOptionId);
        var mealOptionDay = mealOption.getMeals().getDate();
        if (!today.isEqual(mealOptionDay)) {
          log.error(
              "Cannot Avail this Meal Today:{}. Can be only redeemed on:{}", today, mealOptionDay);
          throw new SelectionNotAvailableException(
              String.format(
                  "Cannot Avail this Meal Today:%s. Can be only redeemed on:%s",
                  today, mealOptionDay));
        }
        var user = userService.getUserById(userId);
        var booking = new Bookings().withUser(user).withDate(mealOptionDay);
        availableBooking.claim();
        mealOption.addBooking(booking);
        bookingRepository.save(booking);
        log.debug(
            "Created New Booking with ID:{} for User ID:{} with Meal Option ID:{}",
            booking.getId(),
            userId,
            mealOptionId);
        return booking.getId();
      } else {
        log.error(
            "There are no available Meals for user ID:{}'s selection today:{}", userId, today);
        throw new SelectionNotAvailableException(
            String.format("There are no available Meals for your selection today:%s", today));
      }
    } else {
      log.error("There are no available Meals for user ID:{}'s selection today:{}", userId, today);
      throw new SelectionNotAvailableException(
          String.format("There are no available Meals for your selection today:%s", today));
    }
  }

  @Override
  @Transactional
  public long cancelBooking(@NonNull Bookings booking, long userId) {
    if (Objects.isNull(booking.getId())) {
      log.error("Booking ID cannot be null");
      throw new BookingNotFoundException("Booking ID is null");
    }
    return cancelBookingById(booking.getId(), userId);
  }

  @Override
  @Transactional
  @Secured({Roles.ROLE_ADMINISTRATOR, Roles.ROLE_MANAGER})
  public void deleteBookingById(long id) {
    log.debug("Booking ID:{} is being deleted", id);
    var bookingToBeDeleted = getBookingById(id);
    bookingRepository.delete(bookingToBeDeleted);
    log.debug("Booking ID:{} has been deleted", id);
  }

  @Override
  @Transactional
  public void deleteBooking(@NonNull Bookings booking) {
    if (Objects.isNull(booking.getId())) {
      log.error("Booking ID cannot be null");
      throw new BookingNotFoundException("Booking ID is null");
    }
    deleteBookingById(booking.getId());
  }

  @Override
  public Bookings getBookingById(long id) {
    log.debug("Finding Booking with ID:{}", id);
    var bookingOpt = bookingRepository.findById(id);
    if (bookingOpt.isEmpty()) {
      log.error("Booking not found with ID:{}", id);
      throw new BookingNotFoundException(String.format("Booking Not Found for ID:%s", id));
    }
    log.debug("Found Booking with ID:{}", id);
    return bookingOpt.get();
  }

  @Override
  @Transactional
  public BookingResponseModel availBooking(long id) {
    log.debug("Trying to avail Booking ID:{}", id);
    var booking = getBookingById(id);
    if (Objects.nonNull(booking.getClaimedAt())) {
      log.error("Booking ID:{} is already claimed!", id);
      throw new InvalidBookingOperation(String.format("Booking ID:%s is already claimed!", id));
    }
    var mealOpt = mealsRepository.findByMealOptions_Bookings_Id(booking.getId());
    if (mealOpt.isPresent()) {
      var meal = mealOpt.get();
      if (!meal.isReady()) {
        log.error(
            "Meal:{} with ID:{} is NOT ready for Booking ID:{}",
            meal.getName(),
            meal.getId(),
            booking.getId());
        throw new InvalidBookingOperation(
            String.format("Meal:%s is NOT Ready yet!", meal.getName()));
      }
      log.debug("Meal is Marked Ready for Booking ID:{}", booking.getId());
    } else {
      log.error("Meal is not present for Booking ID{}", booking.getId());
      throw new InvalidBookingOperation("Meal is not present for this Booking");
    }
    booking.availBooking();
    log.debug("Booking ID:{} is being availed", id);
    return bookingResponseModelMapper.transform(booking);
  }

  @Override
  public BookingResponseModel getCurrentBooking(long userId) {
    var today = LocalDate.now();
    log.debug("Finding Booking for User ID:{} for Today:{}", userId, today);
    var bookingOpt = bookingRepository.findByDateAndUser_IdAndCancelledAtNull(today, userId);
    if (bookingOpt.isEmpty()) {
      log.error("User ID:{} does not have booking for today:{}", userId, today);
      throw new BookingNotFoundException("Booking Not Found for today.");
    }
    var booking = bookingOpt.get();
    log.debug("Found Booking ID:{} for User ID:{} on today:{}", booking.getId(), userId, today);
    return bookingResponseModelMapper.transform(booking);
  }

  @Override
  public List<BookingResponseModel> getUpcomingBookings(long userId) {
    var today = LocalDate.now();
    log.debug("Finding Upcoming Bookings for User ID:{} after today:{}", userId, today);
    var bookingList =
        bookingRepository.findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            today, userId);
    log.debug("Found {} Bookings for User ID:{} after today:{}", bookingList.size(), userId, today);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  @Override
  public List<BookingResponseModel> getPreviousBookings(long userId) {
    var today = LocalDate.now();
    log.debug("Finding Previous Bookings for User ID:{} before today:{}", userId, today);
    var bookingList =
        bookingRepository.findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            today, userId);
    log.debug(
        "Found {} Bookings for User ID:{} before today:{}", bookingList.size(), userId, today);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  @Override
  public List<BookingResponseModel> getBookingsByDate(LocalDate date) {
    log.debug("Finding Bookings on Date:{}", date);
    var bookingList = bookingRepository.findByDateAndCancelledAtNull(date);
    log.debug("Found {} Bookings on Date:{}", bookingList.size(), date);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  private void checkIfBookingOwnedByUser(Bookings booking, UserMetadata user) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null
        && auth.getAuthorities().stream()
            .anyMatch(
                a ->
                    a.getAuthority().equals(Roles.ROLE_ADMINISTRATOR)
                        || a.getAuthority().equals(Roles.ROLE_MANAGER)
                        || a.getAuthority().equals(Roles.CATERER))) {
      log.debug(
          "User :{} is not an employee, thus can cancel the Booking ID:{}",
          auth.getName(),
          booking.getId());
      return;
    }
    log.debug("Checking if Booking ID:{} is owned by User ID:{}", booking.getId(), user.getId());
    if (!booking.getUser().equals(user)) {
      log.error("Booking ID:{} does not belong to User ID:{}", booking.getId(), user.getId());
      throw new InvalidBookingOperation(
          String.format(
              "Booking ID:%s does not belong to User ID:%s", booking.getId(), user.getId()));
    }
    log.debug("Booking ID:{} is owned by User ID:{}", booking.getId(), user.getId());
  }
}
