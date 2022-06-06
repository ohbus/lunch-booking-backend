package xyz.subho.lunchbooking.exceptions;

public class InvalidPermissionDataException extends RuntimeException {

  private static final long serialVersionUID = 2910348436337656194L;

  public InvalidPermissionDataException(String message) {
    super(message);
  }
}
