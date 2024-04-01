package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.dto.GradeCreateDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.repository.GradeRepository;
import com.nhnacademy.auth.user.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    public Grade getGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            return optionalGrade.get();
        }else {
            throw new RuntimeException("등록되지 않은 등급입니다.");
        }
    }

    @Override
    public Grade createGrade(GradeCreateDto gradeCreateDto) {
        Grade grade = new Grade().builder()
                .gradeName(gradeCreateDto.getGradeName())
                .accumulateRate(gradeCreateDto.getAccumulateRate())
                .build();
        return gradeRepository.save(grade);
    }

    @Override
    public Grade modifyGrade(Long id, GradeCreateDto gradeCreateDto) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            Grade grade = new Grade().builder()
                    .gradeId(id)
                    .gradeName(gradeCreateDto.getGradeName())
                    .accumulateRate(gradeCreateDto.getAccumulateRate())
                    .build();
            return gradeRepository.save(grade);
        } else {
            throw new RuntimeException("등록되지 않은 등급입니다.");
        }
    }

    @Override
    public Grade deleteGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            return gradeRepository.deleteByGradeId(id);
        } else {
            throw new RuntimeException("등록되지 않은 등급입니다.");
        }
    }
}
