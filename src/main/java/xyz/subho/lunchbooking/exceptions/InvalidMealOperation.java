package xyz.subho.lunchbooking.exceptions;

public class InvalidMealOperation extends RuntimeException {

  public InvalidMealOperation(String message) {
    super(message);
  }
}
