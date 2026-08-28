package com.jecsamtech.kapebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpError> resourceNotFoundException(ResourceNotFoundException err){

        HttpError httpError = new HttpError(HttpStatus.NOT_FOUND.value(), err.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(httpError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<HttpError> resourceAlreadyExistsException(ResourceAlreadyExistsException err){

        HttpError httpError = new HttpError(HttpStatus.CONFLICT.value(), err.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(httpError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpError> illegalArgumentException(IllegalArgumentException err) {
        HttpError httpError = new HttpError(HttpStatus.BAD_REQUEST.value(), err.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<HttpError> passwordMismatchException(PasswordMismatchException err) {
        HttpError httpError = new HttpError(HttpStatus.BAD_REQUEST.value(), err.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    public ResponseEntity<HttpError> responseStatusException(org.springframework.web.server.ResponseStatusException err) {
        HttpError httpError = new HttpError(err.getStatusCode().value(), err.getReason(),
                LocalDateTime.now());
        return new ResponseEntity<>(httpError, err.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpError> notValidException(MethodArgumentNotValidException err){

        Map<String, String> errors = new HashMap<>();
        for (FieldError error: err.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        HttpError error = new HttpError(HttpStatus.BAD_REQUEST.value(), "Petición  incorrecta",
                LocalDateTime.now(), errors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
