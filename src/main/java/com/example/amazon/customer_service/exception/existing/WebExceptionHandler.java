package com.example.amazon.customer_service.exception.existing;

import com.example.amazon.customer_service.response.ApiResponse;
import com.example.amazon.customer_service.exception.BitzException;
import com.example.amazon.customer_service.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleException(final ValidationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .<String>builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(e.getMessage())
                            .code("400")
                        .body(e.getLocalizedMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BitzException.class)
    public ResponseEntity<ApiResponse<String>> handleException(final BitzException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse
                        .<String>builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(e.getMessage())
                        .code(e.getErrorCode())
                        .body(e.getLocalizedMessage())
                        .build());
    }
}
