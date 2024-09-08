package com.example.amazon.customer_service.service;

import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.response.CustomerInfoResponseDto;

import java.io.IOException;

public interface CustomerService {
    String addCustomer(AddCustomerRequestDto requestDto);

    CustomerInfoResponseDto getCustomerInfo(String customerHashId);

    String updateCustomerInfo(UpdateCustomerInfo requestDto);

    String blockCustomer(String customerId);
}
