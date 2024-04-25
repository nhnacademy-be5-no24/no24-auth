package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.entity.*;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {
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
        Customer customer = new Customer().builder()
                .customerNo(customerNo)
                .customerId(customerId)
                .customerPassword(customerPassword)
                .customerName(customerName)
                .customerPhoneNumber(customerPhoneNumber)
                .customerEmail(customerEmail)
                .customerBirthday(customerBirthday)
                .customerRole(customerRole).build();

        Grade grade = new Grade().builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .accumulateRate(accumulateRate)
                .build();

        Member member = new Member().builder()
                .customerNo(customerNo)
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE)
                .build();
        MemberDto memberDto = MemberDto.builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();

        MemberCreateRequest memberCreateRequest = new MemberCreateRequest();
        memberCreateRequest.setCustomerId(customerId);
        memberCreateRequest.setCustomerPassword(customerPassword);
        memberCreateRequest.setCustomerName(customerName);
        memberCreateRequest.setCustomerEmail(customerEmail);
        memberCreateRequest.setCustomerPhoneNumber(customerPhoneNumber);

        memberCreateRequest.setGradeId(gradeId);

        //when
        when(customerRepository.save(any())).thenReturn(customer);
        when(memberRepository.save(any())).thenReturn(member);
        when(gradeRepository.findById(1L)).thenReturn(Optional.ofNullable(grade));

        //then
        MemberDto result = memberService.createMember(memberCreateRequest);
        assertThat(memberDto).isEqualTo(result);

    }

    @Test
    @DisplayName("member 수정")
    void modifyMember() {
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

        Grade grade = new Grade().builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .accumulateRate(accumulateRate)
                .build();

        Member member = new Member().builder()
                .customerNo(customerNo)
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE)
                .build();
        MemberDto memberDto = MemberDto.builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();

        MemberCreateRequest memberCreateRequest = new MemberCreateRequest();
        memberCreateRequest.setCustomerId(customerId);
        memberCreateRequest.setCustomerPassword(customerPassword);
        memberCreateRequest.setCustomerName(customerName);
        memberCreateRequest.setCustomerEmail(customerEmail);
        memberCreateRequest.setCustomerPhoneNumber(customerPhoneNumber);

        memberCreateRequest.setGradeId(gradeId);

        //when
        when(memberRepository.save(any())).thenReturn(member);
        when(gradeRepository.findById(1L)).thenReturn(Optional.ofNullable(grade));
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        //then
        MemberDto result = memberService.modifyMember(1L, memberCreateRequest);
        assertThat(memberDto).isEqualTo(result);

    }

    @Test
    @DisplayName("memeber Id로 조회")
    void getMember() {
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


        Grade grade = new Grade().builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .accumulateRate(accumulateRate)
                .build();


        Member member = new Member().builder()
                .customerNo(customerNo)
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();
        MemberDto memberDto = MemberDto.builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE)
                .build();
        //when
        when(memberRepository.findById(customerNo)).thenReturn(Optional.ofNullable(member));
        //then
        MemberDto result = memberService.getMember(customerNo);
        assertThat(memberDto).isEqualTo(result);
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() {
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

        Grade grade = new Grade().builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .accumulateRate(accumulateRate)
                .build();


        Member member = new Member().builder()
                .customerNo(customerNo)
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();

        //when
        Member updatedMember = new Member().builder()
                .customerNo(member.getCustomerNo())
                .customer(member.getCustomer())
                .memberId(member.getMemberId())
                .lastLoginAt(member.getLastLoginAt())
                .role(member.getRole())
                .grade(member.getGrade())
                .memberState(MemberState.LEAVE).build();
        MemberDto memberDto = MemberDto.builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.LEAVE).build();

        when(memberRepository.save(any())).thenReturn(updatedMember);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //then
        MemberDto result = memberService.deleteMember(1L);
        assertThat(memberDto).isEqualTo(result);
        assertThat(result.getMemberState()).isEqualTo(MemberState.LEAVE);
    }
    @Test
    @DisplayName("memeber 등급별로 조회")
    void getMemberByGrade() {
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


        Grade grade = new Grade().builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .accumulateRate(accumulateRate)
                .build();


        MemberDto member1 = new MemberDto().builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();
        MemberDto member2 = new MemberDto().builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();
        MemberDto member3 = new MemberDto().builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();

        List<MemberDto> memberDtos = new ArrayList<>();
        memberDtos.add(member1);
        memberDtos.add(member2);
        memberDtos.add(member3);

        Page<MemberDto> memberPage = new PageImpl<>(memberDtos);

        MemberDto memberDto = MemberDto.builder()
                .customer(customer)
                .memberId(memberId)
                .lastLoginAt(LastLoginAt)
                .role(role)
                .grade(grade)
                .memberState(MemberState.ACTIVE).build();
        //when
        when(gradeRepository.findById(any())).thenReturn(Optional.ofNullable(grade));
        int offset = 1; // 요청한 페이지 번호
        int pageSize = 2; // 페이지 사이즈


        Pageable pageable = PageRequest.of(pageSize, offset); // 페이징 설정
        when(memberRepository.findAllByGrade(any(), any())).thenReturn(memberPage);
        //then



        Page<MemberDto> result = memberService.getMemberByGradeId(1L, 2,1);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(1); //0,1 page

    }
}