package xyz.subho.lunchbooking.exceptions;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorDetails implements Serializable {
	
	private static final long serialVersionUID = 771838073493840450L;

	private final String message;

    private final long timestamp;

    public ErrorDetails(final String message) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }

}
