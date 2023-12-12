package com.gleestorm.cargo.advice;


import com.gleestorm.cargo.exceptions.UserNotFoundException;
import com.gleestorm.cargo.core.utils.apiResponse.MyApiResponse;
import com.gleestorm.cargo.core.utils.apiResponse.ApiResponseError;
import com.gleestorm.cargo.core.utils.apiResponse.ApiResponseMetadata;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MyApiResponse<Object> handleInvalidArgument(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(itemError -> {
            errorMap.put(itemError.getField(), itemError.getDefaultMessage());
        });

        ApiResponseError apiErr =
                ApiResponseError.builder()
                        .errors(errorMap)
                        .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid argument, please check meta data error")
                        .errors(errorMap).build();

        ApiResponseMetadata metadata = ApiResponseMetadata.builder()
                .error(apiErr)
                .paging(null)
                .processingStart(null)
                .processingEnd(Instant.now())
                .build();

        MyApiResponse<Object> myApiResponse = MyApiResponse.builder().data(null)
                .metadata(metadata).build();

        return myApiResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public MyApiResponse<Object> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());

        ApiResponseError apiErr =
                ApiResponseError.builder()
                        .errors(errorMap)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .errors(errorMap).build();

        ApiResponseMetadata metadata = ApiResponseMetadata.builder()
                .error(apiErr)
                .paging(null)
                .processingStart(null)
                .processingEnd(Instant.now())
                .build();

        return MyApiResponse.builder().data(null)
                .metadata(metadata).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public MyApiResponse<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());

        ApiResponseError apiErr =
                ApiResponseError.builder()
                        .errors(errorMap)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .errors(errorMap).build();

        ApiResponseMetadata metadata = ApiResponseMetadata.builder()
                .error(apiErr)
                .paging(null)
                .processingStart(null)
                .processingEnd(Instant.now())
                .build();

        return MyApiResponse.builder().data(null)
                .metadata(metadata).build();
    }
}
