package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.exception.GradeNotFoundException;
import com.nhnacademy.auth.exception.MemberNotFoundException;
import com.nhnacademy.auth.user.dto.MemberCreateDto;
import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.entity.Role;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.repository.MemberRepository;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerRepository customerRepository;
    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;


    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public Member createMember(MemberCreateDto memberCreateDto) {
        String rawPassword = memberCreateDto.getCustomerPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Customer customer = Customer.builder()
                .customerId(memberCreateDto.getCustomerId())
                .customerPassword(encPassword)
                .customerName(memberCreateDto.getCustomerName())
                .customerPhoneNumber(memberCreateDto.getCustomerPhoneNumber())
                .customerEmail(memberCreateDto.getCustomerEmail())
                .customerBirthday(memberCreateDto.getCustomerBirthday())
                .customerRole(Role.ROLE_MEMBER.toString()).build();

        customer = customerRepository.save(customer);

        Optional<Grade> optionalGrade = gradeRepository.findById(memberCreateDto.getGradeId());
        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            Member member = Member.builder()
                    .customerNo(customer.getCustomerNo())
                    .customer(customer)
                    .memberId(memberCreateDto.getCustomerId())
                    .lastLoginAt(LocalDateTime.now())
                    .grade(grade)
                    .isActive(true)
                    .isLeave(false)
                    .role(Role.ROLE_MEMBER.toString()).build();
            return memberRepository.save(member);
        } else {
            throw new GradeNotFoundException(optionalGrade.get().getGradeId());
        }
    }

    @Override
    public Member modifyMember(Long id, MemberCreateDto memberCreateDto) {
        String rawPassword = memberCreateDto.getCustomerPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Customer optionalCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 고객을 찾을 수 없습니다. " + id));

        Customer customer = Customer.builder()
                .customerNo(optionalCustomer.getCustomerNo())
                .customerId(memberCreateDto.getCustomerId())
                .customerPassword(encPassword)
                .customerName(memberCreateDto.getCustomerName())
                .customerPhoneNumber(memberCreateDto.getCustomerPhoneNumber())
                .customerEmail(memberCreateDto.getCustomerEmail())
                .customerBirthday(memberCreateDto.getCustomerBirthday())
                .customerRole("ROLE_MEMBER").build();
        Grade optionalGrade = gradeRepository.findById(memberCreateDto.getGradeId()).orElseThrow(() -> new RuntimeException("해당 등급을 찾을 수 없습니다. " + memberCreateDto.getGradeId()));
        memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));

        Member member = Member.builder()
                .customerNo(customer.getCustomerNo())
                .customer(customer)
                .memberId(memberCreateDto.getCustomerId())
                .lastLoginAt(LocalDateTime.now())
                .grade(optionalGrade)
                .isActive(true)
                .isLeave(false)
                .role("ROLE_MEMBER").build();
        return memberRepository.save(member);
    }


    @Override
    public Member deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 멤버를 찾을 수 없습니다."));
        Member updatedMember = Member.builder()
                .customerNo(id)
                .customer(member.getCustomer())
                .memberId(member.getMemberId())
                .lastLoginAt(member.getLastLoginAt())
                .grade(member.getGrade())
                .isActive(member.getIsActive())
                .isLeave(true)
                .role(member.getRole()).build();
        return memberRepository.save(updatedMember);


    }
}
