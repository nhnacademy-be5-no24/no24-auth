package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.CustomerCreateDto;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

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

    @BeforeEach
    void setup() {
    }

    @Test
    public void createCustomer() {

        Customer customer = new Customer().builder()
                .customerNo(1L)
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        CustomerCreateDto customerCreateDto = new CustomerCreateDto();
        customerCreateDto.setCustomerId("modified customer");
        customerCreateDto.setCustomerPassword(customerPassword);
        customerCreateDto.setCustomerName(customerName);
        customerCreateDto.setCustomerPhoneNumber(customerPhoneNumber);
        customerCreateDto.setCustomerEmail(customerEmail);
        customerCreateDto.setCustomerBirthday(customerBirthday);

        when(customerRepository.save(any())).thenReturn(customer);

        Customer result = customerService.createCustomer(customerCreateDto);
        assertThat(customerId).isEqualTo(result.getCustomerId());
    }
    @Test
    public void getCustomer() {
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

        //when
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        //then
        Customer result = customerService.getCustomer(1L);
        assertThat(customer).isEqualTo(result);
    }

    @Test
    public void modifyCustomer() {
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

        CustomerCreateDto customerCreateDto = new CustomerCreateDto();
        customerCreateDto.setCustomerId("modified customer");
        customerCreateDto.setCustomerPassword(customerPassword);
        customerCreateDto.setCustomerName(customerName);
        customerCreateDto.setCustomerPhoneNumber(customerPhoneNumber);
        customerCreateDto.setCustomerEmail(customerEmail);
        customerCreateDto.setCustomerBirthday(customerBirthday);

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
        Customer result = customerService.modifyCustomer(1L,customerCreateDto);

        assertThat(modifedCustomer.getCustomerId()).isEqualTo(result.getCustomerId());
        assertThat(modifedCustomer).isEqualTo(result);
    }

}