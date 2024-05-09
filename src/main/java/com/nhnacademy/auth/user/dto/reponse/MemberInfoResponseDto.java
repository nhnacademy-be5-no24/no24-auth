package com.nhnacademy.auth.user.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberInfoResponseDto {
    private String memberId;
    private LocalDateTime lastLoginAt;
    private String role;
}
