package by.it.academy.UserManagementSystem.controller;

import by.it.academy.UserManagementSystem.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * ControllerExceptionHandler is a global exception handling class for REST controllers in the User Management System.
 * It uses Spring's @RestControllerAdvice annotation to provide centralized handling of exceptions.
 *
 * <p>The class defines methods annotated with @ExceptionHandler for specific exception types. When a corresponding
 * exception occurs during request processing in any controller, the corresponding method will be invoked to handle
 * the exception and return an appropriate error response.
 *
 * <p>The methods in this class handle the following exception types:
 * - RuntimeException: Handles runtime exceptions and returns an INTERNAL_SERVER_ERROR (500) response.
 * - NoSuchElementException: Handles NoSuchElementException and returns an INTERNAL_SERVER_ERROR (500) response.
 * - MissingServletRequestParameterException: Handles missing request parameters and returns a BAD_REQUEST (400) response.
 * - HttpRequestMethodNotSupportedException: Handles unsupported HTTP methods and returns a BAD_REQUEST (400) response.
 * - MethodArgumentNotValidException: Handles validation errors from request parameter binding and returns a BAD_REQUEST (400) response.
 *
 * <p>Each method constructs a ResponseError object with an error message and exception details to be included in the response.
 */

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private ResponseError serverErrorRuntime (RuntimeException ex) {
        return new ResponseError("INCORRECT REQUEST", ex.toString());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    private ResponseError noSuchElement (NoSuchElementException ex) {
        return new ResponseError("NO SUCH ELEMENT", ex.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseError messageNotReadable (MissingServletRequestParameterException ex) {
        return new ResponseError("NO CORRECT REQUEST", ex.toString());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseError  handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ResponseError("METHOD NOT ALLOWED", ex.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseError("VALIDATION ERROR", ex.toString());
    }

}
