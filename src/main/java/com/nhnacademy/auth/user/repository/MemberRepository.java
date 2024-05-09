package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> ,MemberRepositoryCustom {
    Page<MemberDto> findAllByGrade(Grade grade, Pageable pageable);
    boolean existsByMemberId(String memberId);
}
