package xyz.subho.lunchbooking.exceptions;

public class PermissionInUseException extends RuntimeException {

  private static final long serialVersionUID = 4624274047380524414L;

  public PermissionInUseException(String message) {
    super(message);
  }
}
