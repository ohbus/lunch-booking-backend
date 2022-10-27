package xyz.subho.lunchbooking.exceptions;

public class SelectionNotAvailableException extends RuntimeException {

  public SelectionNotAvailableException(String message) {
    super(message);
  }
}
