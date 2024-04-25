package com.nhnacademy.auth.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.entity.MemberState;
import com.nhnacademy.auth.user.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;
    ObjectMapper objectMapper = new ObjectMapper();
    MemberCreateRequest memberCreateRequest;
    Member member;
    MemberDto memberDto;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        memberCreateRequest = MemberCreateRequest.builder()
                .customerId("회원")
                .customerPassword("1234")
                .customerName("김회원")
                .customerBirthday(LocalDate.parse("2024-04-02"))
                .customerEmail("kim@gmail.com")
                .customerPhoneNumber("01012345678")
                .gradeId(1L)
                .build();
        member = Member.builder()
                .memberId("회원")

                .grade(Grade.builder()
                        .gradeId(1L)
                        .gradeName("A")
                        .accumulateRate(100L).build())
                .lastLoginAt(LocalDateTime.now())
                .memberState(MemberState.ACTIVE)
                .build();
        memberDto = MemberDto.builder()
                .memberId("회원")
                .grade(Grade.builder()
                        .gradeId(1L)
                        .gradeName("A")
                        .accumulateRate(100L).build())
                .lastLoginAt(LocalDateTime.now())
                .memberState(MemberState.ACTIVE)
                .build();

    }

    @Test
    @DisplayName("회원 생성시 완료")
    @Order(1)
    void createMember_Success() {
        try {
            mockMvc.perform(post("/auth/member/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberCreateRequest)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("회원 단건 조회 완료")
    @Order(2)
    void getMember_Success() {
        try {
            mockMvc.perform(get("/auth/member/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("회원 수정시 완료")
    @Order(3)
    void updateMember_Success() {
        when(memberService.modifyMember(1L, memberCreateRequest)).thenReturn(memberDto);
        try {
            mockMvc.perform(put("/auth/member/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberCreateRequest)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("회원 삭제시 완료")
    @Order(4)
    void deleteMember_Success() {
        try {
            mockMvc.perform(delete("/auth/member/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            )
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
