package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.Customer;
import com.nhnacademy.auth.user.entity.Grade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public class GradeRepositoryTest {
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
        Optional<Grade> savedGrade = gradeRepository.findById(1L);

        //when
        Optional<Grade> result = gradeRepository.findById(savedGrade.get().getGradeId());

        //then
        assertThat(savedGrade).isEqualTo(result);
    }

}