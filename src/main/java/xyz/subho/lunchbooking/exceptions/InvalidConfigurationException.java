package xyz.subho.lunchbooking.exceptions;

public class InvalidConfigurationException extends RuntimeException {

  private static final long serialVersionUID = 9012762751087318428L;

  public InvalidConfigurationException(String message) {
    super(message);
  }
}
