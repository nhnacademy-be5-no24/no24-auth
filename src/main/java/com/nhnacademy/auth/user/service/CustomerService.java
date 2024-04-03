package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.dto.reponse.CustomerDto;
import com.nhnacademy.auth.user.dto.request.CustomerCreateRequest;

public interface CustomerService {
    CustomerDto getCustomer(Long id);

    CustomerDto createCustomer(CustomerCreateRequest customerCreateRequest);

    CustomerDto modifyCustomer(Long id, CustomerCreateRequest customerCreateRequest);

}
