package com.example.amazon.customer_service.exception;


import lombok.Builder;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
public class ValidationException extends NestedRuntimeException{
    @Serial
    private static final long serialVersionUID = -8116876688454470805L;
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String code;
    private String message;

    public ValidationException() {
        super("Default Validation Exception");
    }
    public ValidationException(String message){
        super(message);
        this.message = message;
    }
    public ValidationException(String message,HttpStatus status){
        super(message);
        this.message = message;
        this.status = status;
    }
}
