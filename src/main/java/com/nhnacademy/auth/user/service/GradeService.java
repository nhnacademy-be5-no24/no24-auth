package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.dto.request.GradeCreateDto;
import com.nhnacademy.auth.user.entity.Grade;

public interface GradeService {
    Grade getGrade(Long id);

    Grade createGrade(GradeCreateDto gradeCreateDto);

    Grade modifyGrade(Long id, GradeCreateDto gradeCreateDto);

    Grade deleteGrade(Long id);

}
