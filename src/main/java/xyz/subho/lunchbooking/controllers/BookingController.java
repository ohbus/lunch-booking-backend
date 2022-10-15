package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
}
