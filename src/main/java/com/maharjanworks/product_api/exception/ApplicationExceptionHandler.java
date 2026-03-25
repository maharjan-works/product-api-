package com.maharjanworks.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistException(CategoryAlreadyExistsException ex, WebRequest webRequest) {

        var exceptionResponse = ExceptionResponse.builder()
                .apiPath(webRequest.getDescription(false))
                .httpStatus(HttpStatus.CONFLICT)
                .errorMessage(ex.getMessage())
                .errorExistAt(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest webRequest) {

        var exceptionResponse = ExceptionResponse.builder()
                .apiPath(webRequest.getDescription(false))
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .errorExistAt(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest webRequest){

        var exceptionResponse = ExceptionResponse.builder()
                .apiPath(webRequest.getDescription(false))
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .errorExistAt(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }


}
