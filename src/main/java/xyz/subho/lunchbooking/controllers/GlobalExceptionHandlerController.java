/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.subho.lunchbooking.exceptions.BookingExistsException;
import xyz.subho.lunchbooking.exceptions.BookingNotFoundException;
import xyz.subho.lunchbooking.exceptions.InvalidBookingOperation;
import xyz.subho.lunchbooking.exceptions.InvalidEmailException;
import xyz.subho.lunchbooking.exceptions.InvalidLoginException;
import xyz.subho.lunchbooking.exceptions.InvalidMealOperation;
import xyz.subho.lunchbooking.exceptions.InvalidOtpConfiguration;
import xyz.subho.lunchbooking.exceptions.InvalidPermissionDataException;
import xyz.subho.lunchbooking.exceptions.InvalidRoleDataException;
import xyz.subho.lunchbooking.exceptions.InvalidRoleIdentifierException;
import xyz.subho.lunchbooking.exceptions.InvalidUserDataException;
import xyz.subho.lunchbooking.exceptions.InvalidUserIdentifierException;
import xyz.subho.lunchbooking.exceptions.InvalidUsernameException;
import xyz.subho.lunchbooking.exceptions.MealNotFoundException;
import xyz.subho.lunchbooking.exceptions.PermissionInUseException;
import xyz.subho.lunchbooking.exceptions.PermissionNotFoundException;
import xyz.subho.lunchbooking.exceptions.RoleInUseException;
import xyz.subho.lunchbooking.exceptions.RoleNotFoundException;
import xyz.subho.lunchbooking.exceptions.SelectionLockedException;
import xyz.subho.lunchbooking.exceptions.SelectionNotAvailableException;
import xyz.subho.lunchbooking.exceptions.UserIsSecuredException;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.models.ErrorDetails;

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
    AccessDeniedException.class,
  })
  public ResponseEntity<ErrorDetails> handleAsAccessDenied(RuntimeException ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({
    RoleNotFoundException.class,
    UserNotFoundException.class,
    UserIsSecuredException.class,
    PermissionNotFoundException.class,
    MealNotFoundException.class,
    BookingNotFoundException.class,
    InvalidOtpConfiguration.class,
    UsernameNotFoundException.class
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

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorDetails> handleAsMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {

    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
    ErrorDetails errorDetails;
    try {
      errorDetails = new ErrorDetails(new ObjectMapper().writeValueAsString(errorMap));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler({HttpMessageNotReadableException.class, ValidationException.class})
  public ResponseEntity<ErrorDetails> handleAsUnpronounceableEntity(Exception ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
  public ResponseEntity<ErrorDetails> handleAsMediaTypeNotSupported(Exception ex) {

    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
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
