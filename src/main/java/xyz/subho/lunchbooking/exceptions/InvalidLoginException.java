package xyz.subho.lunchbooking.exceptions;

public class InvalidLoginException extends RuntimeException {

  private static final long serialVersionUID = -5262128016261444859L;

  public InvalidLoginException(String message) {
    super(message);
  }
}
