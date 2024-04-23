package com.nhnacademy.auth.config.jwt;


import com.nhnacademy.auth.config.auth.PrincipalDetails;
import com.nhnacademy.auth.user.entity.Customer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");
        String refreshToken = request.getHeader("RefreshToken");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        String token = authorization.split(" ")[1];
        System.out.println("token is ... ->" + token);

        if (token != null) {
            try {
                String username = jwtUtil.getUsername(token);
                String role = jwtUtil.getRole(token);

                Customer customer = Customer.builder()
                        .customerPassword("tempPassword")
                        .customerRole(role)
                        .build();

                PrincipalDetails principalDetails = new PrincipalDetails(customer);

                Authentication authToken = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch(ExpiredJwtException e) {
                // 토큰 만료 시
                logger.info("token expired");
                filterChain.doFilter(request, response);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
