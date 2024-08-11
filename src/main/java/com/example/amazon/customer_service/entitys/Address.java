package com.example.amazon.customer_service.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "address")
public class Address extends AuditTable{
    @Column(name = "streatName")
    public String streatName;
    @Column(name = "area")
    public String area;
    @Column(name = "city")
    public String city;
    @Column(name = "country")
    public String country;
}
