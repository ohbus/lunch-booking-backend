package xyz.subho.lunchbooking.exceptions;

public class BookingExistsException extends RuntimeException {

  public BookingExistsException(String message) {
    super(message);
  }
}
