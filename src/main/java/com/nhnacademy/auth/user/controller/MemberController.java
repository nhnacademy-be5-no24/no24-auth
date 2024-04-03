package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
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
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getMember(id));
    }

    @PostMapping("/member/create")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberCreateRequest));
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long id,@RequestBody MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.modifyMember(id, memberCreateRequest));
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<MemberDto> deleteMember(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberService.deleteMember(id));
    }
}
