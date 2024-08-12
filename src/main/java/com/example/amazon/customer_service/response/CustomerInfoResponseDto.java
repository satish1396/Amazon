package com.example.amazon.customer_service.response;

import com.example.amazon.customer_service.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponseDto {
    public String firstName;
    @JsonIgnore
    public String lastName;
    public String email;
    public String mobileNo;
    public Status status;
}
