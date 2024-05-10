package com.nhnacademy.auth.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.auth.user.dto.reponse.GradeDto;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GradeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
class GradeControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private GradeService gradeService;
    ObjectMapper objectMapper = new ObjectMapper();
    GradeDto gradeDto;
    Grade grade;
    Grade modifiedGrade;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GradeController(gradeService)).build();
        grade = Grade.builder()
                .gradeId(1L)
                .gradeName("A")
                .accumulateRate(100000L).build();
        gradeDto = GradeDto.builder()
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
                            .content(objectMapper.writeValueAsString(gradeDto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("등급 단건 조회시 완료")
    @Order(2)
    void getGrade_Success() {
        when(gradeService.getGrade(any())).thenReturn(gradeDto);
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
        when(gradeService.modifyGrade(1L, gradeDto)).thenReturn(gradeDto);
        try {
            mockMvc.perform(put("/auth/grade/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(gradeDto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("등급 삭제시 완료")
    @Order(4)
    void deleteGrade_Success() {
        when(gradeService.deleteGrade(1L)).thenReturn(gradeDto);
        try {
            mockMvc.perform(delete("/auth/grade/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
