package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerNo(Long id);

    Customer findByCustomerId(String customerId);
}
