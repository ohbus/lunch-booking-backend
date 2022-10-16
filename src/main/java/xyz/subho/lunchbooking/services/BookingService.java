package xyz.subho.lunchbooking.services;

import xyz.subho.lunchbooking.entities.Bookings;

public interface BookingService {

  public long createBooking(long mealOptionId, long userId);

  public long cancelBookingById(long id);

  public long cancelBooking(Bookings booking);

  public void deleteBookingById(long id);

  public void deleteBooking(Bookings booking);

  public Bookings getBookingById(long id);

  public long availBooking(long id);
}
