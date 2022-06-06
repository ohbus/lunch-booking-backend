package xyz.subho.lunchbooking.exceptions;

public class InvalidMealTypeException extends RuntimeException {

  private static final long serialVersionUID = -5776100941724917950L;

  public InvalidMealTypeException(String message) {
    super(message);
  }
}
