package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.entity.MemberState;

public interface MemberStateService {
    MemberState getMemberState(Long id);

    MemberState createMemberState(String memberStateName);

    MemberState modifyMemberState(Long id,String memberStateName);
}
