package xyz.subho.lunchbooking.services;

import java.time.LocalDate;
import java.util.List;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.models.BookingResponseModel;

public interface BookingService {

  public long createBooking(long mealOptionId, long userId);

  public long cancelBookingById(long id, long userId);

  public long cancelBooking(Bookings booking, long userId);

  public long claimAvailableMeal(long mealOptionId, long userId);

  public void deleteBookingById(long id);

  public void deleteBooking(Bookings booking);

  public Bookings getBookingById(long id);

  public BookingResponseModel availBooking(long id);

  public BookingResponseModel getCurrentBooking(long userId);

  public List<BookingResponseModel> getUpcomingBookings(long userId);

  public List<BookingResponseModel> getPreviousBookings(long userId);

  public List<BookingResponseModel> getBookingsByDate(LocalDate date);
}
