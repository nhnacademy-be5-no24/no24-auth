package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.request.MemberCreateDto;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getMember(id));
    }

    @PostMapping("/member/create")
    public ResponseEntity<Member> createMember(@RequestBody MemberCreateDto memberCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberCreateDto));
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id,@RequestBody MemberCreateDto memberCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.modifyMember(id,memberCreateDto));
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberService.deleteMember(id));
    }
}
