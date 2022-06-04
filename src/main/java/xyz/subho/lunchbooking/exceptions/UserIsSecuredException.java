package xyz.subho.lunchbooking.exceptions;

public class UserIsSecuredException extends RuntimeException {

	private static final long serialVersionUID = 9147910102443102805L;

	public UserIsSecuredException(String message) {
        super(message);
    }

}
