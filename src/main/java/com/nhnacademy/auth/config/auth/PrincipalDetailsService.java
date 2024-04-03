package com.nhnacademy.auth.config.auth;

import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username:{}",username);
        Customer customer = customerRepository.findByCustomerId(username);
        log.info("customer:{}",customer);
        if (customer != null) {
            return new PrincipalDetails(customer);
        }
        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + username);
    }
}
