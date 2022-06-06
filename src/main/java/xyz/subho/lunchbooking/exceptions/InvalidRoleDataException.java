package xyz.subho.lunchbooking.exceptions;

public class InvalidRoleDataException extends RuntimeException {

  private static final long serialVersionUID = 951286109496793029L;

  public InvalidRoleDataException(String message) {
    super(message);
  }
}
