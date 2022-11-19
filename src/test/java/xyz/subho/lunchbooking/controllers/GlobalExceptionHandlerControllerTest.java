package xyz.subho.lunchbooking.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import xyz.subho.lunchbooking.models.ErrorDetails;

class GlobalExceptionHandlerControllerTest {
    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsBadRequest(RuntimeException)}
     */
    @Test
    void testHandleAsBadRequest() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsBadRequestResult = globalExceptionHandlerController
                .handleAsBadRequest(new RuntimeException());
        assertTrue(actualHandleAsBadRequestResult.hasBody());
        assertTrue(actualHandleAsBadRequestResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleAsBadRequestResult.getStatusCode());
        assertNull(actualHandleAsBadRequestResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsBadRequest(RuntimeException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsBadRequest2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.RuntimeException.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsBadRequest(GlobalExceptionHandlerController.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsBadRequest(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsAccessDenied(RuntimeException)}
     */
    @Test
    void testHandleAsAccessDenied() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsAccessDeniedResult = globalExceptionHandlerController
                .handleAsAccessDenied(new RuntimeException());
        assertTrue(actualHandleAsAccessDeniedResult.hasBody());
        assertTrue(actualHandleAsAccessDeniedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.FORBIDDEN, actualHandleAsAccessDeniedResult.getStatusCode());
        assertNull(actualHandleAsAccessDeniedResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsAccessDenied(RuntimeException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsAccessDenied2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.RuntimeException.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsAccessDenied(GlobalExceptionHandlerController.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsAccessDenied(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsNotFound(RuntimeException)}
     */
    @Test
    void testHandleAsNotFound() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsNotFoundResult = globalExceptionHandlerController
                .handleAsNotFound(new RuntimeException());
        assertTrue(actualHandleAsNotFoundResult.hasBody());
        assertTrue(actualHandleAsNotFoundResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleAsNotFoundResult.getStatusCode());
        assertNull(actualHandleAsNotFoundResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsNotFound(RuntimeException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsNotFound2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.RuntimeException.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsNotFound(GlobalExceptionHandlerController.java:71)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsNotFound(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsBadUnauthorized(RuntimeException)}
     */
    @Test
    void testHandleAsBadUnauthorized() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsBadUnauthorizedResult = globalExceptionHandlerController
                .handleAsBadUnauthorized(new RuntimeException());
        assertTrue(actualHandleAsBadUnauthorizedResult.hasBody());
        assertTrue(actualHandleAsBadUnauthorizedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNAUTHORIZED, actualHandleAsBadUnauthorizedResult.getStatusCode());
        assertNull(actualHandleAsBadUnauthorizedResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsBadUnauthorized(RuntimeException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsBadUnauthorized2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.RuntimeException.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsBadUnauthorized(GlobalExceptionHandlerController.java:88)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsBadUnauthorized(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsMethodArgumentNotValid(MethodArgumentNotValidException)}
     */
    @Test
    void testHandleAsMethodArgumentNotValid() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsMethodArgumentNotValidResult = globalExceptionHandlerController
                .handleAsMethodArgumentNotValid(
                        new MethodArgumentNotValidException(null, new BindException("Target", "Object Name")));
        assertTrue(actualHandleAsMethodArgumentNotValidResult.hasBody());
        assertTrue(actualHandleAsMethodArgumentNotValidResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualHandleAsMethodArgumentNotValidResult.getStatusCode());
        assertEquals("{}", actualHandleAsMethodArgumentNotValidResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsMethodArgumentNotValid(MethodArgumentNotValidException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsMethodArgumentNotValid2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.bind.MethodArgumentNotValidException.getBindingResult()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsMethodArgumentNotValid(GlobalExceptionHandlerController.java:97)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsMethodArgumentNotValid(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsUnpronounceableEntity(Exception)}
     */
    @Test
    void testHandleAsUnpronounceableEntity() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsUnpronounceableEntityResult = globalExceptionHandlerController
                .handleAsUnpronounceableEntity(new Exception());
        assertTrue(actualHandleAsUnpronounceableEntityResult.hasBody());
        assertTrue(actualHandleAsUnpronounceableEntityResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualHandleAsUnpronounceableEntityResult.getStatusCode());
        assertNull(actualHandleAsUnpronounceableEntityResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsUnpronounceableEntity(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsUnpronounceableEntity2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsUnpronounceableEntity(GlobalExceptionHandlerController.java:108)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsUnpronounceableEntity(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsMediaTypeNotSupported(Exception)}
     */
    @Test
    void testHandleAsMediaTypeNotSupported() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsMediaTypeNotSupportedResult = globalExceptionHandlerController
                .handleAsMediaTypeNotSupported(new Exception());
        assertTrue(actualHandleAsMediaTypeNotSupportedResult.hasBody());
        assertTrue(actualHandleAsMediaTypeNotSupportedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, actualHandleAsMediaTypeNotSupportedResult.getStatusCode());
        assertNull(actualHandleAsMediaTypeNotSupportedResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsMediaTypeNotSupported(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsMediaTypeNotSupported2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsMediaTypeNotSupported(GlobalExceptionHandlerController.java:115)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsMediaTypeNotSupported(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsConflict(Exception)}
     */
    @Test
    void testHandleAsConflict() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleAsConflictResult = globalExceptionHandlerController
                .handleAsConflict(new Exception());
        assertTrue(actualHandleAsConflictResult.hasBody());
        assertTrue(actualHandleAsConflictResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CONFLICT, actualHandleAsConflictResult.getStatusCode());
        assertNull(actualHandleAsConflictResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleAsConflict(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleAsConflict2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleAsConflict(GlobalExceptionHandlerController.java:128)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleAsConflict(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleEverythingElse(Exception)}
     */
    @Test
    void testHandleEverythingElse() {
        GlobalExceptionHandlerController globalExceptionHandlerController = new GlobalExceptionHandlerController();
        ResponseEntity<ErrorDetails> actualHandleEverythingElseResult = globalExceptionHandlerController
                .handleEverythingElse(new Exception());
        assertTrue(actualHandleEverythingElseResult.hasBody());
        assertTrue(actualHandleEverythingElseResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleEverythingElseResult.getStatusCode());
        assertNull(actualHandleEverythingElseResult.getBody().getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandlerController#handleEverythingElse(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleEverythingElse2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "ex" is null
        //       at xyz.subho.lunchbooking.controllers.GlobalExceptionHandlerController.handleEverythingElse(GlobalExceptionHandlerController.java:135)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandlerController()).handleEverythingElse(null);
    }
}

