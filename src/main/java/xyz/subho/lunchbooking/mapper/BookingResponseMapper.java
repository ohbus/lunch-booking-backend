package xyz.subho.lunchbooking.mapper;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.BookingResponseModel;

@Component("BookingResponseMapper")
public class BookingResponseMapper implements Mapper<Bookings, BookingResponseModel> {

  @Override
  public BookingResponseModel transform(Bookings source) {
    Pair<Long, String> mealOptionIdNamePair =
        source.getBookingsMealOptions().stream()
            .map(
                bookingsMealOptions ->
                    Pair.of(
                        bookingsMealOptions.getMealOptions().getId(),
                        bookingsMealOptions.getMealOptions().getName()))
            .findFirst()
            .orElseGet(() -> Pair.of(0L, ""));
    return new BookingResponseModel(
        source.getId(),
        source.getUser().getFirstName(),
        source.getUser().getLastName(),
        source.getDate(),
        mealOptionIdNamePair.getSecond(),
        mealOptionIdNamePair.getFirst(),
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
