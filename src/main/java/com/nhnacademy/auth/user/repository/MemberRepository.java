package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByCustomer(Customer customer);
}
