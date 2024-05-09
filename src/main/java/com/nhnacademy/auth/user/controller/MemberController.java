package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.config.jwt.JWTUtil;
import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.reponse.MemberInfoResponseDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private final JWTUtil jwtUtil;
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
     * 회원 단건 조회 요청 시 사용되는 메소드입니다.
     *
     * @param token 조회를 위한 해당 회원 Access Token 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @GetMapping("/member/token/{token}")
    public ResponseEntity<MemberInfoResponseDto> getMemberByToken(@PathVariable String token) {
        String memberId = jwtUtil.getUsername(token);
        return ResponseEntity.ok().body(memberService.getMemberByMemberId(memberId));
    }
    /**
     * 회원 생성 조회 요청 시 사용되는 메소드입니다.
     *
     * @param memberCreateRequest 회원을 생성하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PostMapping("/member/create")
    public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberCreateRequest));
    }
    /**
     * 회원 수정 조회 요청 시 사용되는 메소드입니다.
     * @param memberId 회원이 존재하는지 확인하기 위한 id 입니다.
     * @param memberCreateRequest 회원을 수정하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PutMapping("/member/{memberId}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long memberId,@Valid @RequestBody MemberCreateRequest memberCreateRequest) {
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
    /**
     * 등급별 회원 조회 시 사용되는 메소드입니다.
     * @param gradeId 등급을 가져오기 위한 id 입니다.
     * @param pageSize page별 데이터 갯수 입니다.
     * @param offset page별 pageNo 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @GetMapping("/member/grade/{gradeId}")
    public ResponseEntity<Page<MemberDto>> getMembersByGrade(@PathVariable Long gradeId,
                                                             @RequestParam Integer pageSize,
                                                             @RequestParam Integer offset){
        Page<MemberDto> memberDtos = memberService.getMemberByGradeId(gradeId, pageSize, offset);
        return ResponseEntity.ok().body(memberDtos);
    }

    @GetMapping("/member/exist/{memberId}")
    public ResponseEntity<Boolean> existMember(@PathVariable String memberId) {
        return ResponseEntity.ok().body(memberService.existMemberByMemberId(memberId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
