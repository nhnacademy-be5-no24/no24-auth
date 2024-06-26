package com.nhnacademy.auth.user.service;

import com.nhnacademy.auth.user.dto.reponse.MemberDto;
import com.nhnacademy.auth.user.dto.reponse.MemberInfoResponseDto;
import com.nhnacademy.auth.user.dto.request.MemberCreateRequest;
import com.nhnacademy.auth.user.dto.request.MemberVerifyRequest;
import org.springframework.data.domain.Page;


/**
 * 회원(Member) 서비스 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
public interface MemberService {
    /**
     * 특정 회원에 대한 조회를 위한 메소드 입니다.
     * @param id 회원 고유 번호
     * @return 회원 정보를 담은 Dto
     */
    MemberDto getMember(Long id);
    /**
     * 특정 회원에 대한 생성을 위한 메소드 입니다.
     * @param memberCreateRequest
     * @return 회 정보를 담은 Dto
     */
    MemberDto createMember(MemberCreateRequest memberCreateRequest);
    /**
     * 특정 회원에 대한 수정을 위한 메소드 입니다.
     * @param id
     * @param memberCreateRequest
     * @return 회원 정보를 담은 Dto
     */
    MemberDto modifyMember(Long id, MemberCreateRequest memberCreateRequest);
    /**
     * 특정 회원에 대한 삭제를 위한 메소드 입니다.
     * @param id
     * @return 회원 정보를 담은 Dto
     */
    MemberDto deleteMember(Long id);
    /**
     * 특정 회원에 대한 삭제를 위한 메소드 입니다.
     * @param gradeId
     * @return 등급별 회원 dto
     */
    Page<MemberDto> getMemberByGradeId(Long gradeId, Integer pageSize, Integer offset);

    /**
     * 특정 회원에 대한 기본 정보 조회를 위한 메소드 입니다.
     * @param memberId 회원 고유 번호
     * @return 회원 정보를 담은 Dto
     */
    MemberInfoResponseDto getMemberByMemberId(String memberId);

    boolean existMemberByMemberId(String memberId);

    /**
     * customerId와 password가 일치하는 지 확인하는 메소드
     * @param memberVerifyRequest
     * @return boolean
     */
    boolean verifyMember(MemberVerifyRequest memberVerifyRequest);

    /**
     * customerNo로 조회
     * @param customerNo
     * @return
     */
    MemberInfoResponseDto getMemberInfoByCustomerNo(Long customerNo);

    void changeToActive(String customerId);
}
