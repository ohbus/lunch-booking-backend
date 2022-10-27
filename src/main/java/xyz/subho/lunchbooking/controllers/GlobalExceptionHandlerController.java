package xyz.subho.lunchbooking.controllers;

import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.subho.lunchbooking.exceptions.*;

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
    PermissionNotFoundException.class,
    MealNotFoundException.class,
    BookingNotFoundException.class
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

  @ExceptionHandler({HttpMessageNotReadableException.class, ValidationException.class})
  public ResponseEntity<ErrorDetails> handleAsUnpronounceableEntity(Exception ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler({
    BookingExistsException.class,
    InvalidBookingOperation.class,
    SelectionLockedException.class,
    SelectionNotAvailableException.class,
    InvalidMealOperation.class
  })
  public ResponseEntity<ErrorDetails> handleAsConflict(Exception ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorDetails> handleEverythingElse(Exception ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
