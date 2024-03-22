package com.nhnacademy.auth.user.repository;

import com.nhnacademy.auth.user.entity.MemberState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStateRepository extends JpaRepository<MemberState,Long> {
}
