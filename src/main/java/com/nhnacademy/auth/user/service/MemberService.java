package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;

public interface MemberService {
    MemberDto getMember(Long id);
    MemberDto createMember(MemberCreateRequest memberCreateRequest);

    MemberDto modifyMember(Long id, MemberCreateRequest memberCreateRequest);

    MemberDto deleteMember(Long id);
}
