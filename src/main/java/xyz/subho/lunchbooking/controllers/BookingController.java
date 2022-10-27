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
  public BookingResponseModel availBooking(@PathVariable long bookingId, Principal principal) {
    return bookingService.availBooking(bookingId, Long.parseLong(principal.getName()));
  }

  @Secured({Roles.ROLE_CATERER, Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(EndpointPropertyKey.BOOKING_DELETE)
  public void cancelBooking(@PathVariable long bookingId, Principal principal) {
    bookingService.cancelBookingById(bookingId, Long.parseLong(principal.getName()));
  }

  @GetMapping(EndpointPropertyKey.BOOKING_FETCH_FOR_USER)
  public List<BookingResponseModel> getUserBookings(
      @RequestParam("prev") String prev, Principal principal) {
    long userId = Long.parseLong(principal.getName());
    if (StringUtils.isNotBlank(prev)) return bookingService.getPreviousBookings(userId);
    return bookingService.getUpcomingBookings(userId);
  }

  @GetMapping(EndpointPropertyKey.BOOKINGS_BY_DATE)
  public List<BookingResponseModel> getAllBookingsForDate(
      @PathVariable("date") LocalDate date, Principal principal) {
    return bookingService.getBookingsByDate(date);
  }
}
