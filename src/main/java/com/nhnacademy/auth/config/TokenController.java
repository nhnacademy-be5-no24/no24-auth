package com.nhnacademy.auth.config;

import com.nhnacademy.auth.config.auth.TokenDto;
import com.nhnacademy.auth.config.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.nhnacademy.auth.config.jwt.JWTUtil.REFRESH_TOKEN_VALIDITY;
import static com.nhnacademy.auth.config.jwt.JWTUtil.TOKEN_VALIDITY;

/**
 * Token Refresh 및
 *
 * @Author : 박병휘
 * @Date : 2024/04/23
 */
@RestController
@RequestMapping("/auth")
public class TokenController {
    private final JWTUtil jwtUtil;

    public TokenController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/verify")
    public ResponseEntity verifyToken(@RequestHeader("Authorization") String token,
                                      @RequestHeader("RefreshToken") String refreshToken) {
        token = token.replace("Bearer ", "");
        refreshToken = refreshToken.replace("Bearer ", "");
        TokenDto tokenResponse = new TokenDto(token, refreshToken);

        JWTUtil.Status tokenStatus = jwtUtil.isValidJwt(token);
        JWTUtil.Status refreshTokenStatus = jwtUtil.isValidJwt(refreshToken);


        if(tokenStatus == JWTUtil.Status.VALID) {
            // 토큰이 유효한 경우, ok 반환
            return ResponseEntity.ok().body(tokenResponse);
        }
        else if(tokenStatus == JWTUtil.Status.UNAUTHORIZED) {
            // 토큰이 인증되지 않은 경우, unauthorized 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(tokenResponse);
        }
        else {
            // 토큰이 만료된 경우, refresh 토큰 확인.

            // refresh 토큰도 만료된 경우, unauthorized 반환
            if(refreshTokenStatus == JWTUtil.Status.EXPIRED || refreshTokenStatus == JWTUtil.Status.UNAUTHORIZED)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            // refresh 토큰은 만료된 것이 아니라면 새로운 토큰 발급
            String username = jwtUtil.getUsername(refreshToken);
            String role = jwtUtil.getRole(refreshToken);

            String newToken = "Bearer " + jwtUtil.createJwt(username, role, TOKEN_VALIDITY);
            String newRefreshToken = "Bearer " + jwtUtil.createJwt(username, role, REFRESH_TOKEN_VALIDITY);

            tokenResponse = new TokenDto(newToken, newRefreshToken);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(tokenResponse);
        }
    }
}
