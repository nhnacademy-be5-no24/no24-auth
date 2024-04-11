package com.nhnacademy.auth.user.dto.reponse;

import com.nhnacademy.auth.user.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Customer customer;
    private String memberId;
    private LocalDateTime lastLoginAt;
    private Grade grade;
    private String role;
    private MemberStateName memberState;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .customer(member.getCustomer())
                .memberId(member.getMemberId())
                .lastLoginAt(member.getLastLoginAt())
                .grade(member.getGrade())
                .role(member.getRole())
                .memberState(member.getMemberState()).build();
    }

}
