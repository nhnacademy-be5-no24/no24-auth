package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.MemberCreateDto;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    GradeRepository gradeRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    MemberServiceImpl memberService;

    private final Long customerNo = 1L;
    private final String customerId = "member";
    private final String customerPassword = "1234";
    private final String customerName = "김회원";
    private final String customerEmail = "kim@gmail.com";
    private final LocalDate customerBirthday = LocalDate.parse("2024-03-27");
    private final String customerRole = "ROLE_MEMBER";
    private final String customerPhoneNumber = "01023456789";


    private final String memberId = "member";
    private final LocalDateTime LastLoginAt = LocalDateTime.of(2024, 3, 27, 0, 0);
    private final String role = "ROLE_MEMBER";

    private final Long gradeId = 1L;
    private final String gradeName = "A";
    private final Long accumulateRate = 100L;


    @Test
    @DisplayName("member 생성")
    void createMember() {
        //given
        Customer customer = new Customer();
        customer.setCustomerNo(customerNo);
        customer.setCustomerId(customerId);
        customer.setCustomerPassword(customerPassword);
        customer.setCustomerName(customerName);
        customer.setCustomerEmail(customerEmail);
        customer.setCustomerBirthday(customerBirthday);
        customer.setCustomerRole(customerRole);
        customer.setCustomerPhoneNumber(customerPhoneNumber);

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        Member member = new Member();
        member.setCustomerNo(customerNo);
        member.setCustomer(customer);
        member.setMemberId(memberId);
        member.setLastLoginAt(LastLoginAt);
        member.setRole(role);
        member.setGrade(grade);
        member.setIsLeave(false);
        member.setIsActive(true);

        MemberCreateDto memberCreateDto = new MemberCreateDto();
        memberCreateDto.setCustomerId(customerId);
        memberCreateDto.setCustomerPassword(customerPassword);
        memberCreateDto.setCustomerName(customerName);
        memberCreateDto.setCustomerEmail(customerEmail);
        memberCreateDto.setCustomerPhoneNumber(customerPhoneNumber);

        memberCreateDto.setGradeId(gradeId);

        //when
        when(customerRepository.save(any())).thenReturn(customer);
        when(memberRepository.save(any())).thenReturn(member);
        when(gradeRepository.findByGradeId(1L)).thenReturn(grade);

        //then
        Member result = memberService.createMember(memberCreateDto);
        assertThat(member).isEqualTo(result);

    }

    @Test
    @DisplayName("member 수정")
    void modifyMember() {
        //given
        Customer customer = new Customer();
        customer.setCustomerNo(customerNo);
        customer.setCustomerId(customerId);
        customer.setCustomerPassword(customerPassword);
        customer.setCustomerName(customerName);
        customer.setCustomerEmail(customerEmail);
        customer.setCustomerBirthday(customerBirthday);
        customer.setCustomerRole(customerRole);
        customer.setCustomerPhoneNumber(customerPhoneNumber);

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        Member member = new Member();
        member.setCustomerNo(customerNo);
        member.setCustomer(customer);
        member.setMemberId(memberId);
        member.setLastLoginAt(LastLoginAt);
        member.setRole(role);
        member.setGrade(grade);
        member.setIsLeave(false);
        member.setIsActive(true);

        MemberCreateDto memberCreateDto = new MemberCreateDto();
        memberCreateDto.setCustomerId(customerId);
        memberCreateDto.setCustomerPassword(customerPassword);
        memberCreateDto.setCustomerName(customerName);
        memberCreateDto.setCustomerEmail(customerEmail);
        memberCreateDto.setCustomerPhoneNumber(customerPhoneNumber);

        memberCreateDto.setGradeId(gradeId);

        //when
        when(customerRepository.save(any())).thenReturn(customer);
        when(memberRepository.save(any())).thenReturn(member);
        when(gradeRepository.findByGradeId(1L)).thenReturn(grade);
        when(customerRepository.findByCustomerNo(1L)).thenReturn(customer);
        when(memberRepository.findByCustomer(customer)).thenReturn(member);

        //then
        Member result = memberService.modifyMember(1L, memberCreateDto);
        assertThat(member).isEqualTo(result);

    }

    @Test
    @DisplayName("memeber Id로 조회")
    void getMember() {
        //given
        Customer customer = new Customer();
        customer.setCustomerNo(customerNo);
        customer.setCustomerId(customerId);
        customer.setCustomerPassword(customerPassword);
        customer.setCustomerName(customerName);
        customer.setCustomerEmail(customerEmail);
        customer.setCustomerBirthday(customerBirthday);
        customer.setCustomerRole(customerRole);
        customer.setCustomerPhoneNumber(customerPhoneNumber);

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        Member member = new Member();
        member.setCustomerNo(customerNo);
        member.setCustomer(customer);
        member.setMemberId(memberId);
        member.setLastLoginAt(LastLoginAt);
        member.setRole(role);
        member.setGrade(grade);
        member.setIsLeave(false);
        member.setIsActive(true);
        //when
        when(customerRepository.findByCustomerNo(1L)).thenReturn(customer);
        when(memberRepository.findByCustomer(any())).thenReturn(member);
        //then
        Member result = memberService.getMember(1L);
        assertThat(member).isEqualTo(result);
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() {
        //given
        Customer customer = new Customer();
        customer.setCustomerNo(customerNo);
        customer.setCustomerId(customerId);
        customer.setCustomerPassword(customerPassword);
        customer.setCustomerName(customerName);
        customer.setCustomerEmail(customerEmail);
        customer.setCustomerBirthday(customerBirthday);
        customer.setCustomerRole(customerRole);
        customer.setCustomerPhoneNumber(customerPhoneNumber);

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        Member member = new Member();
        member.setCustomerNo(customerNo);
        member.setCustomer(customer);
        member.setMemberId(memberId);
        member.setLastLoginAt(LastLoginAt);
        member.setRole(role);
        member.setGrade(grade);
        member.setIsLeave(false);
        member.setIsActive(true);

        //when
        when(customerRepository.findByCustomerNo(1L)).thenReturn(customer);
        when(memberRepository.findByCustomer(any())).thenReturn(member);
        member.setIsLeave(true);
        when(memberRepository.save(member)).thenReturn(member);

        //then
        Member result = memberService.deleteMember(1L);
        assertThat(member).isEqualTo(result);
        assertThat(true).isEqualTo(result.getIsLeave());
    }
}