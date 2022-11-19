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

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.JSONStyle;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.subho.lunchbooking.exceptions.*;
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
    ErrorDetails errorDetails =
        new ErrorDetails(new JSONObject(errorMap).toJSONString(JSONStyle.MAX_COMPRESS));
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
