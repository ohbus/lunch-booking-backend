package xyz.subho.lunchbooking.services;

import xyz.subho.lunchbooking.entities.Bookings;

import java.awt.print.Book;
import java.util.List;

public interface BookingService {

  public long createBooking(long mealOptionId, long userId);

  public long cancelBookingById(long id, long userId);

  public long cancelBooking(Bookings booking, long userId);

  public void deleteBookingById(long id);

  public void deleteBooking(Bookings booking);

  public Bookings getBookingById(long id);

  public long availBooking(long id, long userId);

  public List<Bookings> getAllBookingForToday();

  public Bookings getBookingForTodayByUser(long userId);

  public List<Bookings> getBookingsInRange();
}
