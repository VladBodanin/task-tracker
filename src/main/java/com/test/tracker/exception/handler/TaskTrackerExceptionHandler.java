package com.test.tracker.exception.handler;

import com.test.tracker.exception.EntityAlreadyExists;
import com.test.tracker.exception.NotFoundException;
import com.test.tracker.exception.TaskTrackerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class TaskTrackerExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {

        log.error(ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "This method is not allowed");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExists(
            EntityAlreadyExists ex) {
        log.error(ex.getMessage(), ex);

        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(TaskTrackerException.class)
    public ResponseEntity<ErrorResponse> handlePlaceServiceException(TaskTrackerException ex) {
        log.error(ex.getMessage(), ex);

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST, ex.getErrorReason().getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        log.error(ex.getMessage(), ex);
        BindingResult result = ex.getBindingResult();
        List<FieldErrorResponse.FieldError> fieldErrors =
                result.getFieldErrors().stream()
                        .map(
                                fieldError ->
                                        new FieldErrorResponse.FieldError(
                                                fieldError.getField(), fieldError.getDefaultMessage()))
                        .collect(Collectors.toList());

        FieldErrorResponse errorResponse =
                new FieldErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), "Method arguments are not valid", fieldErrors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus httpStatus, int customCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse(customCode, message);

        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
