package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.reponse.CustomerDto;
import com.nhnacademy.auth.user.dto.request.CustomerCreateRequest;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    CustomerServiceImpl customerService;

    private final String customerId = "customer1";
    private final String customerPassword = "1234";
    private final String customerName = "김고객";
    private final String customerPhoneNumber = "01012345678";
    private final String customerEmail = "kim@gmail.com";
    private final LocalDate customerBirthday = LocalDate.parse("2024-03-22");
    private final String customerRole = "ROLE_CUSTOMER";

    @Test
    @DisplayName("고객 생성시 완료")
    void createCustomer() {

        Customer customer = new Customer().builder()
                .customerNo(1L)
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();
        CustomerDto customerDto = CustomerDto.builder()
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        customerCreateRequest.setCustomerId("modified customer");
        customerCreateRequest.setCustomerPassword(customerPassword);
        customerCreateRequest.setCustomerName(customerName);
        customerCreateRequest.setCustomerPhoneNumber(customerPhoneNumber);
        customerCreateRequest.setCustomerEmail(customerEmail);
        customerCreateRequest.setCustomerBirthday(customerBirthday);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDto result = customerService.createCustomer(customerCreateRequest);
        assertThat(result).isEqualTo(customerDto);
    }
    @Test
    @DisplayName("고객 단건 조회 완료")
    void getCustomer() {
        //given
        Customer customer = new Customer().builder()
                .customerNo(1L)
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();
        CustomerDto customerDto = CustomerDto.builder()
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        //when
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        //then
        CustomerDto result = customerService.getCustomer(1L);
        assertThat(result).isEqualTo(customerDto);
    }

    @Test
    @DisplayName("고객 수정시 완료")
    void modifyCustomer() {
        //given
        Customer customer = new Customer().builder()
                .customerNo(1L)
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();
        CustomerDto customerDto = CustomerDto.builder()
                .customerId("modifiedCustomer")
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        customerCreateRequest.setCustomerId("modified customer");
        customerCreateRequest.setCustomerPassword(customerPassword);
        customerCreateRequest.setCustomerName(customerName);
        customerCreateRequest.setCustomerPhoneNumber(customerPhoneNumber);
        customerCreateRequest.setCustomerEmail(customerEmail);
        customerCreateRequest.setCustomerBirthday(customerBirthday);

        Customer modifedCustomer = new Customer().builder()
                .customerNo(1L)
                .customerId("modifiedCustomer")
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        //when
        when(customerRepository.save(any())).thenReturn(modifedCustomer);
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        //then
        CustomerDto result = customerService.modifyCustomer(1L, customerCreateRequest);

        assertThat(result.getCustomerId()).isEqualTo(modifedCustomer.getCustomerId());
        assertThat(result).isEqualTo(customerDto);
    }

}