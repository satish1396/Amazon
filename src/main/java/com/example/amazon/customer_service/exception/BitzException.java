package com.example.amazon.customer_service.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
public class BitzException extends NestedRuntimeException {

    @Serial
    private static final long serialVersionUID = 4166179631942640269L;
    String message;
    public BitzException(String message){
        super(message);
        this.message = message;
    }

}
