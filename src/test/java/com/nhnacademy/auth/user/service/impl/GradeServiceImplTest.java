package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.GradeCreateDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.repository.GradeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GradeServiceImplTest {
    @Mock
    GradeRepository gradeRepository;

    @InjectMocks
    GradeServiceImpl gradeService;

    private final String gradeName = "A";
    private final Long accumulateRate = 100000L;

    @Test
    @DisplayName("등급 생성")
    void createGrade() {
        //given
        Grade grade = new Grade();
        grade.setGradeId(1L);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        GradeCreateDto gradeCreateDto = new GradeCreateDto();
        gradeCreateDto.setGradeName(gradeName);
        gradeCreateDto.setAccumulateRate(accumulateRate);

        //when
        when(gradeRepository.save(any())).thenReturn(grade);

        //then
        Grade result = gradeService.createGrade(gradeCreateDto);
        assertThat(grade).isEqualTo(result);
    }

    @Test
    @DisplayName("gradeId로 등급 조회")
    void getGrade() {
        //given
        Grade grade = new Grade();
        grade.setGradeId(1L);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);
        //when
        when(gradeRepository.findByGradeId(anyLong())).thenReturn(grade);

        //then
        Grade result = gradeService.getGrade(1L);
        assertThat(grade).isEqualTo(result);
    }

    @Test
    @DisplayName("id와 gradeDto로 등급 수정")
    void modifyGrade() {
        //given
        Grade grade = new Grade();
        grade.setGradeId(1L);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        GradeCreateDto gradeCreateDto = new GradeCreateDto();
        gradeCreateDto.setGradeName(gradeName);
        gradeCreateDto.setAccumulateRate(accumulateRate);

        Grade modifiedGrade = new Grade();
        modifiedGrade.setGradeId(1L);
        modifiedGrade.setGradeName("B");
        modifiedGrade.setAccumulateRate(200000L);

        //when
        when(gradeRepository.findByGradeId(1L)).thenReturn(grade);
        when(gradeRepository.save(any())).thenReturn(modifiedGrade);

        //then
        Grade result = gradeService.modifyGrade(1L, gradeCreateDto);
        assertThat(modifiedGrade).isEqualTo(result);
    }

    @Test
    @DisplayName("id로 등급 삭제")
    void deleteGrade() {
        //given
        Grade grade = new Grade();
        grade.setGradeId(1L);
        grade.setGradeName(gradeName);
        grade.setAccumulateRate(accumulateRate);

        //when
        when(gradeRepository.deleteByGradeId(1L)).thenReturn(grade);

        //then
        Grade result = gradeService.deleteGrade(1L);
        assertThat(grade).isEqualTo(result);

    }

}