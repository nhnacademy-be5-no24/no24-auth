package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.exception.CustomerNotFoundException;
import com.nhnacademy.auth.user.dto.request.CustomerCreateDto;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.entity.Role;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    @Transactional
    @Override
    public Customer createCustomer(CustomerCreateDto customerCreateDto) {
        String rawPassword = customerCreateDto.getCustomerPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        Customer customer = Customer.builder()
                .customerId(customerCreateDto.getCustomerId())
                .customerPassword(encPassword)
                .customerName(customerCreateDto.getCustomerName())
                .customerPhoneNumber(customerCreateDto.getCustomerPhoneNumber())
                .customerEmail(customerCreateDto.getCustomerEmail())
                .customerBirthday(customerCreateDto.getCustomerBirthday())
                .customerRole(Role.ROLE_CUSTOMER.toString()).build();
        return customerRepository.save(customer);
    }
    @Transactional
    @Override
    public Customer modifyCustomer(Long id,CustomerCreateDto customerCreateDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            String rawPassword = customerCreateDto.getCustomerPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            Customer customer = Customer.builder()
                    .customerId(customerCreateDto.getCustomerId())
                    .customerPassword(encPassword)
                    .customerName(customerCreateDto.getCustomerName())
                    .customerPhoneNumber(customerCreateDto.getCustomerPhoneNumber())
                    .customerEmail(customerCreateDto.getCustomerEmail())
                    .customerBirthday(customerCreateDto.getCustomerBirthday())
                    .customerRole(Role.ROLE_CUSTOMER.toString()).build();
            return customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException(id);
        }

    }
}
