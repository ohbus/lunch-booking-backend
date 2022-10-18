package xyz.subho.lunchbooking.services.impl;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.exceptions.BookingNotFoundException;
import xyz.subho.lunchbooking.exceptions.InvalidBookingOperation;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.services.BookingService;
import xyz.subho.lunchbooking.services.MealsService;
import xyz.subho.lunchbooking.services.UserService;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

  @Autowired private BookingRepository bookingRepository;

  @Autowired private MealsService mealsService;

  @Autowired private UserService userService;

  @Override
  @Transactional
  @Secured({

  })
  public long createBooking(long mealOptionId, long userId) {

    var user = userService.getUserById(userId);
    var mealOption = mealsService.getMealOptionById(mealOptionId);
    var date = mealOption.getMeals().getDate();
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
    }
    var booking = new Bookings().withUser(user).withDate(date);
    log.debug("Creating Booking for UserID:{} and Meal Options ID:{}", userId, mealOptionId);
    booking = bookingRepository.save(booking);
    mealOption.addBooking(booking);
    return booking.getId();
  }

  @Override
  @Transactional
  public long cancelBookingById(long id, long userId) {
    var booking = getBookingById(id);
    var user = userService.getUserById(userId);
    if (Objects.nonNull(booking.getCancelledAt())) {
      log.error("Booking ID:{} is already cancelled", id);
      throw new InvalidBookingOperation(String.format("Booking ID:%s is already cancelled!", id));
    }
    checkIfBookingOwnedByUser(booking, user);
    return booking.cancelBooking();
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
  @Secured({Roles.ROLE_ADMINISTRATOR})
  public void deleteBookingById(long id) {
    log.debug("Booking ID:{} is being deleted", id);
    var bookingToBeDeleted = getBookingById(id);
    bookingRepository.delete(bookingToBeDeleted);
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
  @Secured({
          Roles.ROLE_CATERER,
          Roles.ROLE_MANAGER,
          Roles.ROLE_ADMINISTRATOR
  })
  public long availBooking(long id, long userId) {
    var booking = getBookingById(id);
    var user = userService.getUserById(userId);
    if (Objects.nonNull(booking.getClaimedAt())) {
      log.error("Booking ID:{} is already claimed!", id);
      throw new InvalidBookingOperation(String.format("Booking ID:%s is already claimed!", id));
    }
    checkIfBookingOwnedByUser(booking, user);
    return booking.availBooking();
  }

  @Override
  public List<Bookings> getAllBookingForToday() {
    return null;
  }

  @Override
  public Bookings getBookingForTodayByUser(long userId) {
    return null;
  }

  @Override
  public List<Bookings> getBookingsInRange() {
    return null;
  }

  private void checkIfBookingOwnedByUser(Bookings booking, UserMetadata user) {
    if (booking.getUser().equals(user)) {
      log.error("Booking ID:{} does not belong to User ID:{}", booking.getId(), user.getId());
      throw new InvalidBookingOperation(String.format("Booking ID:%s does not belong to User ID:%s", booking.getId(), user.getId()));
    }
  }
}
