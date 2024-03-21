package com.nhnacademy.auth.user.service.impl;

import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.repository.CustomerRepository;
import com.nhnacademy.auth.user.repository.MemberRepository;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final CustomerRepository customerRepository;
    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long id) {
        return null;
    }

    @Override
    public Member createMember(Member member) {
        return null;
    }

    @Override
    public Member modifyMember(Member member) {
        return null;
    }

    @Override
    public Member deleteMember(Long id) {
        return null;
    }
}
