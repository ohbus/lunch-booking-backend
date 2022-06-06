package xyz.subho.lunchbooking.exceptions;

public class RoleNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 827090247231147989L;

  public RoleNotFoundException(String message) {
    super(message);
  }
}
