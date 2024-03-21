package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.entity.Member;

public interface MemberService {
    Member getMember(Long id);
    Member createMember(Member member);

    Member modifyMember(Member member);

    Member deleteMember(Long id);
}
