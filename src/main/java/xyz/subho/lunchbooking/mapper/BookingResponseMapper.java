package xyz.subho.lunchbooking.mapper;

import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.BookingResponseModel;

@Component("BookingResponseMapper")
public class BookingResponseMapper implements Mapper<Bookings, BookingResponseModel> {

  @Override
  public BookingResponseModel transform(Bookings source) {
    return new BookingResponseModel(
        source.getId(),
        source.getUser().getFirstName(),
        source.getUser().getLastName(),
        source.getDate(),
        source.getBookingsMealOptions().stream()
            .map(bookingsMealOptions -> bookingsMealOptions.getMealOptions().getName())
            .findFirst()
            .orElseGet(() -> ""),
        source.getClaimedAt());
  }

  @Override
  public Bookings transformBack(BookingResponseModel source) {
    var booking = new Bookings();
    booking.setId(source.id());
    booking.setUser(
        new UserMetadata().withFirstName(source.firstName()).withLastName(source.lastName()));
    booking.setClaimedAt(source.availedAt());
    return booking.withDate(source.date());
  }
}
