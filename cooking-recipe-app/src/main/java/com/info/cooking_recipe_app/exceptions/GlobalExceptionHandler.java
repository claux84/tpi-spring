package com.info.cooking_recipe_app.exceptions;


import java.time.LocalDateTime;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.info.cooking_recipe_app.dto.errors.ErrorDto;

import com.info.cooking_recipe_app.dto.errors.ValidationErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var errorList= ex.getFieldErrors().stream()
                .map(fieldError -> new ValidationErrorDto(
                                                          HttpStatus.BAD_REQUEST.value(),
                                                          LocalDateTime.now(),
                                                          fieldError.getField(),
                                                          fieldError.getDefaultMessage(),
                                                          ObjectUtils.nullSafeToString(fieldError.getRejectedValue())))
                .toList();


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorList);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){

        ErrorDto errorDto = new ErrorDto(
                                        HttpStatus.NOT_FOUND.value(),
                                        LocalDateTime.now(),
                                        ex.getMessage(),
                                        webRequest.getDescription(false)
                                        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),ex.getMessage(), ObjectUtils.nullSafeToString(ex.getValue()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageReadableException(HttpMessageNotReadableException ex){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),ex.getMessage(),ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);

    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ErrorDto> handleJpaSystemException(JpaSystemException ex){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),ex.getMessage(), ex.getMostSpecificCause().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException ex){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }


  

}
