package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.entity.MemberState;
import com.nhnacademy.auth.user.repository.MemberStateRepository;
import com.nhnacademy.auth.user.service.MemberStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberStateServiceImpl implements MemberStateService {
    private final MemberStateRepository memberStateRepository;

    @Override
    public MemberState getMemberState(Long id) {
        return memberStateRepository.findById(id).orElse(null);
    }

    @Override
    public MemberState createMemberState(String memberStateName) {
        MemberState memberState = new MemberState();
        memberState.setMemberStateName(memberStateName);
        return memberStateRepository.save(memberState);
    }

    @Override
    public MemberState modifyMemberState(Long id,String memberStateName) {
        MemberState memberState = memberStateRepository.findById(id).orElse(null);
        memberState.setMemberStateName(memberStateName);
        return memberStateRepository.save(memberState);
    }
}
