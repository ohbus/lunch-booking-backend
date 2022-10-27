package xyz.subho.lunchbooking.exceptions;

public class MealNotFoundException extends RuntimeException {

  public MealNotFoundException(String message) {
    super(message);
  }
}
