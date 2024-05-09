package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.reponse.MemberInfoResponseDto;
import com.nhnacademy.auth.user.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
public interface MemberRepositoryCustom {
    Page<MemberDto> findMemberByGradeId(Grade grade, Pageable pageable);

    MemberInfoResponseDto findMemberByMemberId(String memberId);
}
