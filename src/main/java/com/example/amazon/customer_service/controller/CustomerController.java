package com.example.amazon.customer_service.controller;

import com.example.amazon.customer_service.response.ApiResponse;
import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.service.CustomerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    public CustomerService customerService;
    @Autowired
    public CustomerController(final CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping("/addCustomer")
    public ApiResponse addCustomerInfo(@RequestBody AddCustomerRequestDto requestDto){
        return ApiResponse
                .builder()
                .message(customerService.addCustomer(requestDto))
                .build();
    }

    @GetMapping("/getCustomerInfo/{customerHashId}")
    public ApiResponse getCustomer(@PathVariable(name = "customerHashId") final String customerHashId){
        return ApiResponse.builder()
                .body(customerService.getCustomerInfo(customerHashId))
                .message("Successfully fetched customer details")
                .build();
    }

    @PutMapping("/updateCustomerInfo")
    public ApiResponse updateCustomer(@RequestBody UpdateCustomerInfo requestDto){
        return ApiResponse.builder()
                .message(customerService.updateCustomerInfo(requestDto))
                .build();
    }

    @DeleteMapping("/block/customer/{customerId}")
    public ApiResponse blockCustomer(@PathVariable(name = "customerId") @NotNull(message = "customerId is mandatory") final String customerId){
        return ApiResponse.builder()
                .message(customerService.blockCustomer(customerId))
                .build();
    }
}
