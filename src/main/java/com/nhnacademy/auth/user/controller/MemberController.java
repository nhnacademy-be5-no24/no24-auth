package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.MemberCreateDto;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMember(id);
    }

    @PostMapping("/member/create")
    public Member createMember(@RequestBody MemberCreateDto memberCreateDto) {
        return memberService.createMember(memberCreateDto);
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable Long id,@RequestBody MemberCreateDto memberCreateDto) {
        return memberService.modifyMember(id,memberCreateDto);
    }

    @PutMapping("/member/delete/{id}")
    public Member deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }
}
