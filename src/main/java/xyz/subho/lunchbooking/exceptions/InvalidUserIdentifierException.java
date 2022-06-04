package xyz.subho.lunchbooking.exceptions;

public class InvalidUserIdentifierException extends RuntimeException {

  private static final long serialVersionUID = 5243604405076578701L;

  public InvalidUserIdentifierException(String message) {
    super(message);
  }
}
