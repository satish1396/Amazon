package com.example.amazon.customer_service.repository;

import com.example.amazon.customer_service.entitys.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

    Optional<CustomerInfo> findByMobileNoAndEmail(String mobileNo, String email);

    Optional<CustomerInfo> findByCustomerHashId(String customerHashId);

    Optional<CustomerInfo> findByCustomerHashIdAndEmail(String customerHashId, String email);

    Optional<CustomerInfo> findByEmail(String email);

    Optional<CustomerInfo> findByMobileNo(String mobileNo);
}
