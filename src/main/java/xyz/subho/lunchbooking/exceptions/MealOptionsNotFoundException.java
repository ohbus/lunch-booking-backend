package xyz.subho.lunchbooking.exceptions;

public class MealOptionsNotFoundException extends RuntimeException {
  public MealOptionsNotFoundException(String message) {
    super(message);
  }
}
