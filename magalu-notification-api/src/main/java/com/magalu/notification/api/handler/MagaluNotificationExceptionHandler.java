package com.magalu.notification.api.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.core.exception.RecordNotFoundException;
import com.magalu.notification.core.exception.data.BusinessExceptionResult;
import com.magalu.notification.core.exception.data.ResponseMessage;
import com.magalu.notification.core.util.Constants;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller advice that handles exceptions thrown by the controllers.
 */
@Slf4j
@RestControllerAdvice
public class MagaluNotificationExceptionHandler {
    private ResponseEntity<ResponseMessage> createErrorsResponseEntity(String errorMessage, 
            HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/problem+json");
        log.error(errorMessage);

        return new ResponseEntity<>(new ResponseMessage(httpStatus.value(), errorMessage), headers, httpStatus);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleRecordNotFoundException(RecordNotFoundException e) {
        return createErrorsResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseMessage> handleValidationException(ValidationException e) {
        return createErrorsResponseEntity(Constants.INVALID_FIELDS, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionResult> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(new BusinessExceptionResult(e), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return createErrorsResponseEntity("Invalid login or password", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>(0);
        errors.put("Description", Constants.INVALID_FIELDS);
        errors.putAll(ex.getBindingResult().getAllErrors().stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .collect(Collectors.toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage
                )));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> handleConstraintViolationException(ConstraintViolationException e) {
        return createErrorsResponseEntity(Constants.INVALID_FIELDS, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccesDeniedException(AccessDeniedException ex) {
        return createErrorsResponseEntity("Insufficient privileges", HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseMessage> handleDataAccessException(DataAccessException ex) {
        return createErrorsResponseEntity("Failed to execute database statement", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(Exception ex) {
        return createErrorsResponseEntity("Unexpected request error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseMessage> haneldMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return createErrorsResponseEntity("Missing request parameter: " + ex.getParameterName(), 
        		HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseMessage> handleBadCredentialsException(BadCredentialsException e) {
        return createErrorsResponseEntity("Invalid login os password", HttpStatus.UNAUTHORIZED);
    }
}
