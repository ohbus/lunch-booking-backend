package xyz.subho.lunchbooking.exceptions;

public class RoleInUseException extends RuntimeException {

  private static final long serialVersionUID = -4158399132834035665L;

  public RoleInUseException(String message) {
    super(message);
  }
}
