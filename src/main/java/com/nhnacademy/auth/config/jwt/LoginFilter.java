package com.nhnacademy.auth.config.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.auth.config.auth.LoginDto;
import com.nhnacademy.auth.config.auth.PrincipalDetails;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, MemberRepository memberRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // LoginDto 객체를 HttpServletRequest에서 가져옴
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        // 인증 매니저를 사용하여 사용자를 인증하고 인증 객체 반환
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        PrincipalDetails customUserDetails = (PrincipalDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        Long customerNo = customUserDetails.getCustomerNo();
        Member member = memberRepository.findById(customerNo).get();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, customerNo, JWTUtil.TOKEN_VALIDITY);
        String refreshToken = jwtUtil.createJwt(username, role, customerNo, JWTUtil.REFRESH_TOKEN_VALIDITY);
        String status = member.getMemberState().toString();

        System.out.println("token is "+token);
        System.out.println("refresh token is "+refreshToken);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("RefreshToken", "Bearer " + refreshToken);
        response.addHeader("MemberStatus", status);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }
}
