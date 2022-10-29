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

package xyz.subho.lunchbooking.controllers;

import io.micrometer.core.instrument.util.StringUtils;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.models.CreateBookingResponseModel;
import xyz.subho.lunchbooking.services.BookingService;

@Slf4j
@RestController
public class BookingController {

  @Autowired private BookingService bookingService;

  @PostMapping(EndpointPropertyKey.BOOKING_CREATE)
  @ResponseStatus(HttpStatus.CREATED)
  public CreateBookingResponseModel createBooking(
      @PathVariable long mealOptionId, Principal principal) {
    return new CreateBookingResponseModel(
        bookingService.createBooking(mealOptionId, Long.parseLong(principal.getName())));
  }

  @PutMapping(EndpointPropertyKey.BOOKING_AVAIL)
  @Secured({Roles.ROLE_CATERER, Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public BookingResponseModel availBooking(@PathVariable long bookingId) {
    return bookingService.availBooking(bookingId);
  }

  @Secured({Roles.ROLE_CATERER, Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(EndpointPropertyKey.BOOKING_ID)
  public void cancelBooking(@PathVariable long bookingId, Principal principal) {
    bookingService.cancelBookingById(bookingId, Long.parseLong(principal.getName()));
  }

  @GetMapping(EndpointPropertyKey.BOOKING_FOR_TODAY)
  public BookingResponseModel getTodayBookingForUser(Principal principal) {
    return bookingService.getCurrentBooking(Long.parseLong(principal.getName()));
  }

  @GetMapping(EndpointPropertyKey.BOOKING_FETCH_FOR_USER)
  public List<BookingResponseModel> getUserBookings(
      @RequestParam(value = "prev", required = false) String prev, Principal principal) {
    long userId = Long.parseLong(principal.getName());
    if (StringUtils.isNotBlank(prev)) return bookingService.getPreviousBookings(userId);
    return bookingService.getUpcomingBookings(userId);
  }

  @GetMapping(EndpointPropertyKey.BOOKINGS_BY_DATE)
  public List<BookingResponseModel> getAllBookingsForDate(@PathVariable("date") LocalDate date) {
    return bookingService.getBookingsByDate(date);
  }

  @PutMapping(EndpointPropertyKey.BOOKING_PICK_UP)
  public CreateBookingResponseModel createBookingFromAvailableOptions(
      @PathVariable long mealOptionId, Principal principal) {
    return new CreateBookingResponseModel(
        bookingService.claimAvailableMeal(mealOptionId, Long.parseLong(principal.getName())));
  }
}
