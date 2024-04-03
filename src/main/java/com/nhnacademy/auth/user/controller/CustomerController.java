package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.CustomerCreateDto;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customer/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerCreateDto));
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateDto customerCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.modifyCustomer(id, customerCreateDto));
    }

}
