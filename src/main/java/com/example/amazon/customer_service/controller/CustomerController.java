package com.example.amazon.customer_service.controller;

import com.example.amazon.customer_service.ApiResponse;
import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.service.CustomerService;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/customer")
public class CustomerController {
    public CustomerService customerService;
    @Autowired
    CustomerController(final CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping(name = "/addCustomer")
    public ApiResponse addCustomer(@RequestBody AddCustomerRequestDto requestDto){
        return ApiResponse.builder().message(customerService.addCustomer(requestDto)).build();
    }

    @PostMapping(name = "/getCustomerInfo/{customerHashId}")
    public ApiResponse getCustomerInfo(@RequestParam @NotBlank(message = "customer hashId is mandetory") final String customerHashId){
        return ApiResponse.builder().body(customerService.getCustomerInfo(customerHashId)).build();
    }

    @PostMapping(name = "/updateCustomerInfo")
    public ApiResponse updateCustomerInfo(@RequestBody UpdateCustomerInfo requestDto){
        return ApiResponse.builder().message(customerService.updateCustomerInfo(requestDto)).build();
    }
}
