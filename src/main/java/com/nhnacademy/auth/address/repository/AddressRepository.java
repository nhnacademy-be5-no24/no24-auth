package com.nhnacademy.auth.address.repository;

import com.nhnacademy.auth.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByMemberCustomerNo(Long customerNo);
}
