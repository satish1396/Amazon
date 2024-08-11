package com.example.amazon.customer_service;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {
    public HttpStatus status;
    public String code;
    public Object body;
    public String message;
}
