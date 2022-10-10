package xyz.subho.lunchbooking.exceptions;

public class InvalidEmailException extends RuntimeException {

  private static final long serialVersionUID = 7058477926614688350L;

  public InvalidEmailException(String message) {
    super(message);
  }
}
