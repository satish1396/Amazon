package com.example.amazon.customer_service.service.imp;

import com.example.amazon.customer_service.entitys.CustomerInfo;
import com.example.amazon.customer_service.exception.ValidationException;
import com.example.amazon.customer_service.repository.CustomerInfoRepository;
import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.response.CustomerInfoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ValidationService {

    public CustomerInfoRepository customerInfoRepository;

    @Autowired
    ValidationService(final CustomerInfoRepository customerInfoRepository){
        this.customerInfoRepository = customerInfoRepository;
    }
    public void validatedAddCustoemrDetails(AddCustomerRequestDto requestDto, Optional<CustomerInfo> customerInfo){
        if(customerInfo.isPresent()){
           log.error("MobileNo. or Email id already exists");
           throw new ValidationException("User already exists");
        }
    }
    public void validateUpdateCustomerInfo(CustomerInfo existingCustomerInfo, UpdateCustomerInfo requestDto){
        if(!Strings.isBlank(requestDto.getEmail()) && requestDto.getEmail().equals(existingCustomerInfo.getEmail())){
            log.info("Existing email and updated email can't be same");
            throw new ValidationException("Existing email and updated email can't be same");
        }

        if(!Strings.isBlank(requestDto.getMobileNo()) && requestDto.getMobileNo().equals(existingCustomerInfo.getMobileNo())){
            log.info("Existing mobileNo. and updated mobileNo. can't be same");
            throw new ValidationException("Existing mobileNo. and updated mobileNo. can't be same");
        }

        Optional<CustomerInfo> checkUpdatedEmailExists = customerInfoRepository.findByEmail(requestDto.getEmail());
        if(checkUpdatedEmailExists.isPresent()){
            log.info("Updated emailId already exists");
            throw new ValidationException("Updated emailId already exists");
        }

        Optional<CustomerInfo> checkUpdatedMobileNoExists = customerInfoRepository.findByMobileNo(requestDto.getMobileNo());
        if(checkUpdatedMobileNoExists.isPresent()){
            log.info("Updated mobile no. already exists");
            throw new ValidationException("Updated mobile no. already exists");
        }

    }

}
