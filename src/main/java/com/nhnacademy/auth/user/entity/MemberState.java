package com.nhnacademy.auth.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberState {
    @Id
    @Column(name = "member_state_id")
    private Long memberStateId;
    @Column(name = "member_state_name")
    private String memberStateName;

}
