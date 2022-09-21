package xyz.subho.lunchbooking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.subho.lunchbooking.exceptions.ErrorDetails;
import xyz.subho.lunchbooking.exceptions.InvalidEmailException;
import xyz.subho.lunchbooking.exceptions.InvalidLoginException;
import xyz.subho.lunchbooking.exceptions.InvalidPermissionDataException;
import xyz.subho.lunchbooking.exceptions.InvalidRoleDataException;
import xyz.subho.lunchbooking.exceptions.InvalidRoleIdentifierException;
import xyz.subho.lunchbooking.exceptions.InvalidUserDataException;
import xyz.subho.lunchbooking.exceptions.InvalidUserIdentifierException;
import xyz.subho.lunchbooking.exceptions.InvalidUsernameException;
import xyz.subho.lunchbooking.exceptions.PermissionInUseException;
import xyz.subho.lunchbooking.exceptions.PermissionNotFoundException;
import xyz.subho.lunchbooking.exceptions.RoleInUseException;
import xyz.subho.lunchbooking.exceptions.RoleNotFoundException;
import xyz.subho.lunchbooking.exceptions.UserIsSecuredException;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

  @ExceptionHandler({
    InvalidUsernameException.class,
  })
  public ResponseEntity<ErrorDetails> handleAsBadRequest(RuntimeException ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
    RoleNotFoundException.class,
    UserNotFoundException.class,
    UserIsSecuredException.class,
    PermissionNotFoundException.class
  })
  public ResponseEntity<ErrorDetails> handleAsNotFound(RuntimeException ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler({
	    InvalidEmailException.class,
	    InvalidUserDataException.class,
	    InvalidUserIdentifierException.class,
	    InvalidRoleIdentifierException.class,
	    InvalidLoginException.class,
	    InvalidPermissionDataException.class,
	    InvalidRoleDataException.class,
	    RoleInUseException.class,
	    PermissionInUseException.class
	  })
	  public ResponseEntity<ErrorDetails> handleAsBadUnauthorized(RuntimeException ex) {

	    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	  }
}
