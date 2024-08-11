package com.example.amazon.customer_service.service;

import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.response.CustomerInfoResponseDto;

import java.io.IOException;

public interface CustomerService {
    public String addCustomer(AddCustomerRequestDto requestDto);

    public CustomerInfoResponseDto getCustomerInfo(String customerHashId);

    public String updateCustomerInfo(UpdateCustomerInfo requestDto);
}
