package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @DisplayName("고유Id로 고객 찾기")
    void findByCustomerNo() {
        //given
        Customer customer = new Customer().builder()
                .customerId("customer")
                .customerPassword("1234")
                .customerName("김고객")
                .customerBirthday(LocalDate.parse("2024-03-28"))
                .customerEmail("kim@gmail.com")
                .customerPhoneNumber("01012345678")
                .customerRole("ROLE_CUSTOMER")
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        //when
        Optional<Customer> result = customerRepository.findById(savedCustomer.getCustomerNo());

        //then
        assertThat(savedCustomer).isEqualTo(result.get());
    }

    @Test
    @DisplayName("아이디로 고객 찾기")
    void findByCustomerId() {
        //given
        Customer customer = new Customer().builder()
                .customerId("customer")
                .customerPassword("1234")
                .customerName("김고객")
                .customerBirthday(LocalDate.parse("2024-03-28"))
                .customerEmail("kim@gmail.com")
                .customerPhoneNumber("01012345678")
                .customerRole("ROLE_CUSTOMER")
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        //when
        Customer result = customerRepository.findByCustomerId(savedCustomer.getCustomerId());

        //then
        assertThat(savedCustomer).isEqualTo(result);
    }

}