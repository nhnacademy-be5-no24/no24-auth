package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.Grade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@ActiveProfiles("dev")
@WebAppConfiguration
class GradeRepositoryTest {
    @Autowired
    GradeRepository gradeRepository;

    @Test
    @DisplayName("아이디로 고객 찾기")
    void findByGradeId() {
        //given
        Grade grade = new Grade().builder()
                .gradeId(1L)
                .gradeName("A")
                .accumulateRate(1000L).build();
        gradeRepository.save(grade);
        Optional<Grade> savedGrade = gradeRepository.findById(1L);

        //when
        Optional<Grade> result = gradeRepository.findById(savedGrade.get().getGradeId());

        //then
        assertThat(result).isEqualTo(savedGrade);
    }

}