package com.example.amazon.customer_service.exception;

import lombok.Data;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
public class BitzException extends NestedRuntimeException {
    @Serial
    private static final long serialVersionUID = 3320220591236480453L;
    public HttpStatus status;
    public String errorCode;
    public String message;

    public BitzException(String message){
        super(message);
        this.message = message;
    }
    public BitzException() {
        super("Default BizException");
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
