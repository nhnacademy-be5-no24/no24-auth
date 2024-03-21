package com.nhnacademy.auth.config.auth;

import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginprocessingurl("/login);
//login 요청이 오면 자동으로 UserDetailService 타입으로 ioc되어 이쓴ㄴ loadUserByUsername 함수가 실행됨.
@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
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
