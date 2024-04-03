package com.nhnacademy.auth.user.controller;


import com.nhnacademy.auth.user.dto.reponse.CustomerDto;
import com.nhnacademy.auth.user.dto.request.CustomerCreateRequest;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * 고객(Customer) RestController 입니다.
 *
 * @author : 김병주
 * @date : 2024-03-29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{customerNo}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerNo) {
        CustomerDto customerDto = customerService.getCustomer(customerNo);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/customer/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerCreateRequest));
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.modifyCustomer(id, customerCreateRequest));
    }

}
