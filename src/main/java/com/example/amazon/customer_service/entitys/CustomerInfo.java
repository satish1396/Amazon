package com.example.amazon.customer_service.entitys;

import com.example.amazon.customer_service.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.io.Serial;

@Entity
@Table(name = "customer_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo extends AuditTable{

    @Serial
    private static final long serialVersionUID = -8437283222612949368L;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name")
    @Nullable
    private String lastName;

    @Column(name = "mobile_no",nullable = false,unique = true)
    private String mobileNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "customer_hash_id",nullable = false,unique = true)
    private String customerHashId;

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException("com.example.amazon.customer_service.entitys.CustomerInfo");
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        throw new java.io.NotSerializableException("com.example.amazon.customer_service.entitys.CustomerInfo");
    }
}
