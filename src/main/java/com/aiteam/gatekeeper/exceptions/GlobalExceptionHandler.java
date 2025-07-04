package com.aiteam.gatekeeper.exceptions;

import com.aiteam.gatekeeper.dtos.responses.ErrorApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorApiResponse> handleGlobalException(RuntimeException exception) {
        ErrorApiResponse errorResponse = new ErrorApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorApiResponse> handleSQLException(DataIntegrityViolationException exception) {
        ErrorApiResponse errorResponse = new ErrorApiResponse(HttpStatus.CONFLICT.value(), "Data integrity violation: " + Objects.requireNonNull(exception.getRootCause()).getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
