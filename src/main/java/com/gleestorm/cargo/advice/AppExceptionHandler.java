package com.gleestorm.cargo.advice;


import com.gleestorm.cargo.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(itemError -> {
            errorMap.put(itemError.getField(), itemError.getDefaultMessage());
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());

        return errorMap;
    }
}
