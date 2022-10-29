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
