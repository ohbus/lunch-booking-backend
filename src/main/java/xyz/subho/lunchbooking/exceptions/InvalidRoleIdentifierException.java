package xyz.subho.lunchbooking.exceptions;

public class InvalidRoleIdentifierException extends RuntimeException {

	private static final long serialVersionUID = 4371821340603405006L;

	public InvalidRoleIdentifierException(String message) {
        super(message);
    }

}
