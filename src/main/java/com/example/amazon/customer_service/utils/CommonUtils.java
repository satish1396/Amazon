package com.example.amazon.customer_service.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUtils {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
