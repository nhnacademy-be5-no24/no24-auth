package com.nhnacademy.auth.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_password")
    private String customerPassword;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_birthday")
    private LocalDate customerBirthday;
    @Column(name = "customer_type")
    private Boolean customerType;
}
