package com.nhnacademy.auth.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
    @OneToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;       //class 이름 사용안됨
    @OneToOne
    @JoinColumn(name = "member_state_id")
    private MemberState memberState;

}
