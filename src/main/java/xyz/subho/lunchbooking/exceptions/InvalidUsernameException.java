package xyz.subho.lunchbooking.exceptions;

public class InvalidUsernameException extends RuntimeException {

  private static final long serialVersionUID = -1159639964254100405L;

  public InvalidUsernameException(String message) {
    super(message);
  }
}
