package com.nhnacademy.auth.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.auth.user.dto.reponse.CustomerDto;
import com.nhnacademy.auth.user.dto.request.CustomerCreateRequest;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CustomerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    ObjectMapper objectMapper = new ObjectMapper();
    CustomerCreateRequest customerCreateRequest;
    Customer customer;
    Customer modifiedCustomer;
    CustomerDto customerDto;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
         customer = Customer.builder()
                .customerNo(1L)
                .customerId("고객1")
                .customerPassword("1234")
                .customerName("김고객")
                .customerPhoneNumber("01012345678")
                .customerEmail("kim@gmail.com")
                .customerBirthday(LocalDate.parse("2024-04-01"))
                .customerRole("ROLE_CUSTOMER").build();
        customerCreateRequest = CustomerCreateRequest.builder()
                .customerId("고객2")
                .customerPassword("1234")
                .customerName("임고객")
                .customerPhoneNumber("01012345678")
                .customerEmail("kim@gmail.com")
                .customerBirthday(LocalDate.parse("2024-04-01")).build();
        modifiedCustomer =Customer.builder()
                .customerNo(1L)
                .customerId("고객1")
                .customerPassword("1234")
                .customerName("임고객")
                .customerPhoneNumber("01012345678")
                .customerEmail("kim@gmail.com")
                .customerBirthday(LocalDate.parse("2024-04-01"))
                .customerRole("ROLE_CUSTOMER").build();
        customerDto = CustomerDto.builder()
                .customerId("고객1")
                .customerPassword("1234")
                .customerName("임고객")
                .customerPhoneNumber("01012345678")
                .customerEmail("kim@gmail.com")
                .customerBirthday(LocalDate.parse("2024-04-01")).build();
    }

    @Test
    @DisplayName("고객 생성시 완료")
    @Order(1)
    void createCustomer_Success() {
        try {
            mockMvc.perform(post("/auth/customer/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(customerCreateRequest)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("고객 단건 조회 완료")
    @Order(2)
    void getCustomer_Success() {
        when(customerService.getCustomer(anyLong())).thenReturn(customerDto);
        try {
            mockMvc.perform(get("/auth/customer/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("고객 수정 완료")
    @Order(3)
    void updateCustomer_Success() {
        when(customerService.modifyCustomer(1L, customerCreateRequest)).thenReturn(customerDto);
        try {
            mockMvc.perform(put("/auth/customer/update/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(customerCreateRequest)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
