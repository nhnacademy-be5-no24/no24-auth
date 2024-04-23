package com.nhnacademy.auth.config.auth;

import lombok.*;

/**
 * Token 재발급 시 필요한 dto
 *
 * @Author : 박병휘
 * @Date : 2024/04/23
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TokenDto {
    private String authorization;
    private String refreshToken;
}
