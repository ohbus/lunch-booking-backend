package xyz.subho.lunchbooking.services.impl;

import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.Bookings;
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
  public long createBooking(long mealOptionId, long userId) {
    var user = userService.getUserById(userId);
    var mealOption = mealsService.getMealOptionById(mealOptionId);
    var booking = new Bookings().withUser(user).withDate(mealOption.getMeals().getDate());
    log.debug("Creating Booking for UserID:{} and Meal Options ID:{}", userId, mealOptionId);
    mealOption.addBooking(booking);
    return 0;
  }
}
