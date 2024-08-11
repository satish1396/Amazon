package com.example.amazon.customer_service.service.imp;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.amazon.customer_service.entitys.CustomerInfo;
import com.example.amazon.customer_service.enums.Status;
import com.example.amazon.customer_service.exception.BitzException;
import com.example.amazon.customer_service.exception.ValidationException;
import com.example.amazon.customer_service.repository.CustomerInfoRepository;
import com.example.amazon.customer_service.request.AddCustomerRequestDto;
import com.example.amazon.customer_service.request.UpdateCustomerInfo;
import com.example.amazon.customer_service.response.CustomerInfoResponseDto;
import com.example.amazon.customer_service.service.CustomerService;
import com.example.amazon.customer_service.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {

    CustomerInfoRepository customerInfoRepository;
    ValidationService validationService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CustomerServiceImp(final CustomerInfoRepository customerInfoRepository,ValidationService validationService){
        this.customerInfoRepository = customerInfoRepository;
        this.validationService = validationService;
    }

    public String addCustomer(AddCustomerRequestDto requestDto){
        try {
            log.info("Onboarding customer for mobileNo: {} and emailId: {}", requestDto.getMobileNo(), requestDto.getEmail());
            Optional<CustomerInfo> customerInfo = customerInfoRepository.findByMobileNoAndEmail(requestDto.getMobileNo(), requestDto.getEmail());
            validationService.validatedAddCustoemrDetails(requestDto, customerInfo);

            initiateCustomerInfo(requestDto);
        }catch (ValidationException ex){
            throw new ValidationException(ex.getMessage());
        }catch (Exception e){
            throw new BitzException(e.getMessage());
        }
        return "Customer Onboarded Successfully";
    }

    public void initiateCustomerInfo(AddCustomerRequestDto requestDto){
        CustomerInfo customerInfo = CustomerInfo.builder()
                .customerHashId(CommonUtils.generateUUID())
                .email(requestDto.getEmail())
                .mobileNo(requestDto.getMobileNo())
                .status(Status.ACTIVE)
                .address(requestDto.getAddress())
                .lastName(requestDto.getLastName())
                .build();
        customerInfoRepository.save(customerInfo);
    }

    @Override
    public CustomerInfoResponseDto getCustomerInfo(String customerHashId){
        log.info("Fetch customer info for customerHashId: {}",customerHashId);
        CustomerInfoResponseDto responseDto = new CustomerInfoResponseDto();
        try {
            Optional<CustomerInfo> customerInfo = customerInfoRepository.findByCustomerHashId(customerHashId);

            if (!customerInfo.isPresent()) {
                log.error("User not found for customerHashId: {}", customerHashId);
                throw new ValidationException("User not found");
            }
            responseDto = objectMapper.readValue((DataInput) customerInfo.get(), CustomerInfoResponseDto.class);
        }catch (ValidationException e){
            log.error(e.getMessage(),e);
            new ValidationException(e.getMessage());
        }catch (IOException ex){
            throw new BitzException(ex.getMessage());
        }catch (Exception e){
            log.error("Error while fetching the customer Info");
            throw new BitzException(e.getMessage());
        }
        return responseDto;
    }

    @Override
    public String updateCustomerInfo(UpdateCustomerInfo requestDto) {
        log.info("Updating customerInfo for EmailId: {} and mobileNo: {}",requestDto.getEmail(),requestDto.getMobileNo());
        try {
            Optional<CustomerInfo> existingCustomerInfo = customerInfoRepository.findByCustomerHashIdAndEmail(requestDto.getCustomerHashId(), requestDto.getExistingEmail());
            if (!existingCustomerInfo.isPresent()) {
                log.info("Invalid existing email");
                throw new ValidationException("Provide valid existing emailId");
            }

            validationService.validateUpdateCustomerInfo(existingCustomerInfo.get(), requestDto);

            initiateUpdateCustomerInfo(existingCustomerInfo.get(), requestDto);
        }catch (ValidationException e){
            log.info(e.getMessage(),e);
            throw new ValidationException(e.getMessage());
        }catch (Exception e){
            log.info(e.getMessage(),e);
            throw new BitzException(e.getMessage());
        }
        return "Customer updated successfully!";
    }

    public void initiateUpdateCustomerInfo(CustomerInfo customerInfo,UpdateCustomerInfo requestDto){
        if(!Strings.isBlank(requestDto.getEmail())){
            customerInfo.setEmail(requestDto.getEmail());
        }
        if(!Strings.isBlank(requestDto.getMobileNo())){
            customerInfo.setMobileNo(requestDto.getMobileNo());
        }
        if(!Strings.isBlank(requestDto.getAddress())){
            customerInfo.setAddress(requestDto.getAddress());
        }
        if(!Strings.isBlank(requestDto.getFirstName())){
            customerInfo.setFirstName(requestDto.getFirstName());
        }
        if(!Strings.isBlank(requestDto.getLastName())){
            customerInfo.setLastName(requestDto.getLastName());
        }
        customerInfoRepository.save(customerInfo);
    }

}



