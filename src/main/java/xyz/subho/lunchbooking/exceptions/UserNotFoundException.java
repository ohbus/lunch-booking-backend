package xyz.subho.lunchbooking.exceptions;

public class UserNotFoundException extends java.lang.RuntimeException {

  private static final long serialVersionUID = -8224435385490138458L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
