package xyz.subho.lunchbooking.exceptions;

public class InvalidUserDataException extends java.lang.RuntimeException {

    private static final long serialVersionUID = 3666328640465990017L;

	public InvalidUserDataException(String message) {
        super(message);
    }

}
