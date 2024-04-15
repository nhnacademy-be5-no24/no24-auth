package com.nhnacademy.auth.user.repository.impl;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.entity.QMember;
import com.nhnacademy.auth.user.repository.MemberRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }
    QMember member = QMember.member;

    @Override
    public Page<MemberDto> findMemberByGradeId(Grade grade, Pageable pageable) {
        JPQLQuery<Long> count = from(member)
                .select(member.count());
        List<MemberDto> content = from(member)
                .select(Projections.constructor(MemberDto.class,
                        member.memberId,
                        member.customer,
                        member.lastLoginAt,
                        member.grade,
                        member.role,
                        member.isActive,
                        member.isLeave
                        ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return PageableExecutionUtils.getPage(content,pageable,count::fetchCount);
    }
}
