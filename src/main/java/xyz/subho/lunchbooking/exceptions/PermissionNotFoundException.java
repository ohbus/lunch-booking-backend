package xyz.subho.lunchbooking.exceptions;

public class PermissionNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8757124961963418514L;

  public PermissionNotFoundException(String message) {
    super(message);
  }
}
