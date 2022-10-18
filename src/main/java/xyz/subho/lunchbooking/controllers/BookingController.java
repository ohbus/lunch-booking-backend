package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.models.AvailBookingResponseModel;
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
  public AvailBookingResponseModel availBooking(
          @PathVariable long bookingId, Principal principal) {
    return new AvailBookingResponseModel(
            bookingService.availBooking(bookingId, Long.parseLong(principal.getName())));
  }

  @Secured({
          Roles.ROLE_CATERER,
          Roles.ROLE_MANAGER,
          Roles.ROLE_ADMINISTRATOR
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(EndpointPropertyKey.BOOKING_DELETE)
  public void cancelBooking(@PathVariable long bookingId, Principal principal) {
    bookingService.cancelBookingById(bookingId, Long.parseLong(principal.getName()));
  }
}
