package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.exception.GradeNotFoundException;
import com.nhnacademy.auth.exception.MemberNotFoundException;
import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.entity.*;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.repository.MemberRepository;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 회원(Member) 서비스 구현체입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerRepository customerRepository;
    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;


    @Override
    @Transactional(readOnly = true)
    public MemberDto getMember(Long id) {
        Member member =memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        return MemberDto.of(member);
    }

    @Override
    @Transactional
    public MemberDto createMember(MemberCreateRequest memberCreateRequest) {
        String rawPassword = memberCreateRequest.getCustomerPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Customer customer = Customer.builder()
                .customerId(memberCreateRequest.getCustomerId())
                .customerPassword(encPassword)
                .customerName(memberCreateRequest.getCustomerName())
                .customerPhoneNumber(memberCreateRequest.getCustomerPhoneNumber())
                .customerEmail(memberCreateRequest.getCustomerEmail())
                .customerBirthday(memberCreateRequest.getCustomerBirthday())
                .customerRole(Role.ROLE_MEMBER.toString())
                .build();

        customer = customerRepository.save(customer);

        Grade grade = gradeRepository.findById(1L).get();

        Member member = memberRepository.save(Member.builder()
                .customerNo(customer.getCustomerNo())
                .customer(customer)
                .memberId(memberCreateRequest.getCustomerId())
                .lastLoginAt(LocalDateTime.now())
                .grade(grade)
                .memberState(MemberStateName.ACTIVE)
                .role(Role.ROLE_MEMBER.toString()).build());

        return MemberDto.of(member);

    }

    @Override
    @Transactional
    public MemberDto modifyMember(Long id, MemberCreateRequest memberCreateRequest) {
        String rawPassword = memberCreateRequest.getCustomerPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Customer optionalCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 고객을 찾을 수 없습니다. " + id));

        Customer customer = Customer.builder()
                .customerNo(optionalCustomer.getCustomerNo())
                .customerId(memberCreateRequest.getCustomerId())
                .customerPassword(encPassword)
                .customerName(memberCreateRequest.getCustomerName())
                .customerPhoneNumber(memberCreateRequest.getCustomerPhoneNumber())
                .customerEmail(memberCreateRequest.getCustomerEmail())
                .customerBirthday(memberCreateRequest.getCustomerBirthday())
                .customerRole("ROLE_MEMBER").build();
        Grade optionalGrade = gradeRepository.findById(memberCreateRequest.getGradeId()).orElseThrow(() -> new RuntimeException("해당 등급을 찾을 수 없습니다. " + memberCreateRequest.getGradeId()));

        Member member = memberRepository.save(Member.builder()
                .customerNo(customer.getCustomerNo())
                .customer(customer)
                .memberId(memberCreateRequest.getCustomerId())
                .lastLoginAt(LocalDateTime.now())
                .grade(optionalGrade)
                .memberState(MemberStateName.ACTIVE)
                .role("ROLE_MEMBER").build());
        return MemberDto.of(member);
    }


    @Override
    @Transactional
    public MemberDto deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 멤버를 찾을 수 없습니다."));
        Member updatedMember = memberRepository.save(Member.builder()
                .customerNo(id)
                .customer(member.getCustomer())
                .memberId(member.getMemberId())
                .lastLoginAt(member.getLastLoginAt())
                .grade(member.getGrade())
                .memberState(MemberStateName.LEAVE)
                .role(member.getRole()).build());
        return MemberDto.of(updatedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberDto> getMemberByGradeId(Long gradeId,Integer pageSize,Integer offset) {
        Pageable pageable = PageRequest.of(pageSize, offset);
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(()->new GradeNotFoundException(gradeId));
        return memberRepository.findAllByGrade(grade,pageable);
    }
}
