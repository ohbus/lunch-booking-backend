package xyz.subho.lunchbooking.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.exceptions.BookingNotFoundException;
import xyz.subho.lunchbooking.exceptions.InvalidBookingOperation;
import xyz.subho.lunchbooking.exceptions.SelectionLockedException;
import xyz.subho.lunchbooking.exceptions.SelectionNotAvailableException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.BookingResponseModel;
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

  @Autowired
  @Qualifier("BookingResponseMapper")
  private Mapper<Bookings, BookingResponseModel> bookingResponseModelMapper;

  @Override
  @Transactional
  public long createBooking(long mealOptionId, long userId) {

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
      mealOption.removeBooking(existingBooking);
    }
    var booking = new Bookings().withUser(user).withDate(date);
    log.debug("Creating Booking for UserID:{} and Meal Options ID:{}", userId, mealOptionId);
    booking = bookingRepository.save(booking);
    mealOption.addBooking(booking);
    return booking.getId();
  }

  private void checkMealValidity(Meals meal) {
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
    mealsService.getMealOptionsByBookingId(id).removeBookingById(id);
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
  @Secured({Roles.ROLE_ADMINISTRATOR, Roles.ROLE_MANAGER})
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
  public BookingResponseModel availBooking(long id) {
    var booking = getBookingById(id);
    if (Objects.nonNull(booking.getClaimedAt())) {
      log.error("Booking ID:{} is already claimed!", id);
      throw new InvalidBookingOperation(String.format("Booking ID:%s is already claimed!", id));
    }
    booking.availBooking();
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
        bookingRepository.findByDateGreaterThanAndUser_IdOrderByDateAsc(today, userId);
    log.debug("Found {} Bookings for User ID:{} after today:{}", bookingList.size(), userId, today);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  @Override
  public List<BookingResponseModel> getPreviousBookings(long userId) {
    var today = LocalDate.now();
    log.debug("Finding Previous Bookings for User ID:{} before today:{}", userId, today);
    var bookingList = bookingRepository.findByDateLessThanAndUser_IdOrderByDateDesc(today, userId);
    log.debug(
        "Found {} Bookings for User ID:{} before today:{}", bookingList.size(), userId, today);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  @Override
  public List<BookingResponseModel> getBookingsByDate(LocalDate date) {
    log.debug("Finding Bookings on Date:{}", date);
    var bookingList = bookingRepository.findByDate(date);
    log.debug("Found {} Bookings on Date:{}", bookingList.size(), date);
    return bookingList.stream()
        .map(booking -> bookingResponseModelMapper.transform(booking))
        .toList();
  }

  private void checkIfBookingOwnedByUser(Bookings booking, UserMetadata user) {
    if (booking.getUser().equals(user)) {
      log.error("Booking ID:{} does not belong to User ID:{}", booking.getId(), user.getId());
      throw new InvalidBookingOperation(
          String.format(
              "Booking ID:%s does not belong to User ID:%s", booking.getId(), user.getId()));
    }
  }
}
