package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.dto.reponse.GradeDto;

public interface GradeService {
    GradeDto getGrade(Long id);

    GradeDto createGrade(GradeDto gradeDto);

    GradeDto modifyGrade(Long id, GradeDto gradeDto);

    GradeDto deleteGrade(Long id);

}
