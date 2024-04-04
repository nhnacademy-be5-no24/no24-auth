package com.nhnacademy.auth.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.auth.user.dto.request.GradeCreateDto;
import com.nhnacademy.auth.user.entity.Grade;

import com.nhnacademy.auth.user.service.GradeService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GradeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
class GradeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GradeService gradeService;
    ObjectMapper objectMapper = new ObjectMapper();
    GradeCreateDto gradeCreateDto;
    Grade grade;
    Grade modifiedGrade;

    @BeforeEach
    void setup() {
        grade = Grade.builder()
                .gradeId(1L)
                .gradeName("A")
                .accumulateRate(100000L).build();
        gradeCreateDto = GradeCreateDto.builder()
                .gradeName("B")
                .accumulateRate(100L).build();
        modifiedGrade = Grade.builder()
                .gradeId(1L)
                .gradeName("B")
                .accumulateRate(100L).build();
    }
    @Test
    @DisplayName("등급 생성시 완료")
    @Order(1)
    void createGrade_Success() {
        try {
            mockMvc.perform(post("/auth/grade/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(gradeCreateDto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("등급 단건 조회시 완료")
    @Order(2)
    void getGrade_Success() {
        when(gradeService.getGrade(any())).thenReturn(grade);
        try {
            mockMvc.perform(get("/auth/grade/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("등급 수정시 완료")
    @Order(3)
    void updateGrade_Success() {
        when(gradeService.modifyGrade(1L,gradeCreateDto)).thenReturn(modifiedGrade);
        try {
            mockMvc.perform(put("/auth/grade/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(gradeCreateDto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("등급 삭제시 완료")
    @Order(4)
    void deleteGrade_Success() {
        when(gradeService.deleteGrade(1L)).thenReturn(modifiedGrade);
        try {
            mockMvc.perform(delete("/auth/grade/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
