package xyz.subho.lunchbooking.exceptions;

public class SelectionLockedException extends RuntimeException {

  public SelectionLockedException(String message) {
    super(message);
  }
}
