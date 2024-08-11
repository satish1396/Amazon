package com.example.amazon.customer_service.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateCustomerInfo {
    public String firstName;

    public String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email id")
    public String email;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email id")
    @NotNull(message = "Existing email is mandatory")
    public String existingEmail;

    @NotNull(message = "CustomerHashId is mandatory")
    public String customerHashId;

    public String address;

    @Pattern(regexp = "[0-9]{1,}",message = "Alphabets and special characters are not allowed in mobile number")
    public String mobileNo;
}
