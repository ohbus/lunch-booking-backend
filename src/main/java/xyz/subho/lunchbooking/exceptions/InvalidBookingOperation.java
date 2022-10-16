package xyz.subho.lunchbooking.exceptions;

public class InvalidBookingOperation extends RuntimeException {

  public InvalidBookingOperation(String message) {
    super(message);
  }
}
