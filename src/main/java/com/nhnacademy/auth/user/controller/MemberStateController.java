package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.entity.MemberState;
import com.nhnacademy.auth.user.service.MemberStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberStateController {
    private final MemberStateService memberStateService;

    @GetMapping("/memberState/{id}")
    public MemberState getMemberState(@PathVariable Long id) {
        return memberStateService.getMemberState(id);
    }

    @PostMapping("/memberState/create")
    public MemberState createMemberState(@RequestBody String name) {
        return memberStateService.createMemberState(name);
    }

    @PutMapping("/memberState/{id}")
    public MemberState updateMemberState(@PathVariable Long id, @RequestBody String name) {
        return memberStateService.modifyMemberState(id, name);
    }
}
