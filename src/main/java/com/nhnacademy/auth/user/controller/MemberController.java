package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.entity.Member;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * 회원(Member) RestController 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;
    /**
     * 회원 단건 조회 요청 시 사용되는 메소드입니다.
     *
     * @param memberId 조회를 위한 해당 회원 id 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }
    /**
     * 회원 생성 조회 요청 시 사용되는 메소드입니다.
     *
     * @param memberCreateRequest 회원을 생성하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PostMapping("/member/create")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberCreateRequest));
    }
    /**
     * 회원 수정 조회 요청 시 사용되는 메소드입니다.
     * @param memberId 회원이 존재하는지 확인하기 위한 id 입니다.
     * @param memberCreateRequest 회원을 수정하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PutMapping("/member/{memberId}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long memberId,@RequestBody MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.modifyMember(memberId, memberCreateRequest));
    }
    /**
     * 회원 삭제 요청 시 사용되는 메소드입니다.
     * @param memberId 회원을 삭제하기 위한 id 입니다.
     * @return 성공했을 때 응답코드 204 NO_CONTENT 반환합니다.
     */
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<MemberDto> deleteMember(@PathVariable Long memberId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberService.deleteMember(memberId));
    }
}
