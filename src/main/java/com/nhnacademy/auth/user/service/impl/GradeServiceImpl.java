package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.exception.GradeNotFoundException;
import com.nhnacademy.auth.user.dto.request.GradeCreateDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    @Transactional(readOnly = true)
    public Grade getGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            return optionalGrade.get();
        }else {
            throw new GradeNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Grade createGrade(GradeCreateDto gradeCreateDto) {
        Grade grade = Grade.builder()
                .gradeName(gradeCreateDto.getGradeName())
                .accumulateRate(gradeCreateDto.getAccumulateRate())
                .build();
        return gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public Grade modifyGrade(Long id, GradeCreateDto gradeCreateDto) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            Grade grade = Grade.builder()
                    .gradeId(id)
                    .gradeName(gradeCreateDto.getGradeName())
                    .accumulateRate(gradeCreateDto.getAccumulateRate())
                    .build();
            return gradeRepository.save(grade);
        } else {
            throw new GradeNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Grade deleteGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            return gradeRepository.deleteByGradeId(id);
        } else {
            throw new GradeNotFoundException(id);
        }
    }
}
