package com.nhnacademy.auth.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(Long id) {
        super("Member not found "+ id);
    }
}
