package com.example.amazon.customer_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

@Data
public class AddCustomerRequestDto {

    @NotBlank(message = "first name ID is mandatory")
    public String firstName;

    public String lastName;

    @NotBlank(message = "Email ID is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email id")
    public String email;

    @NotBlank(message = "address ID is mandatory")
    public String address;

    @NotBlank(message = "mobileNo ID is mandatory")
    @Pattern(regexp = "[0-9]{1,}",message = "Alphabets and special characters are not allowed in mobile number")
    public String mobileNo;
}