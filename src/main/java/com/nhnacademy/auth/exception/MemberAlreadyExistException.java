package com.nhnacademy.auth.exception;

public class MemberAlreadyExistException extends RuntimeException{
    public MemberAlreadyExistException(String id) {
        super(id + " already exist");
    }
}
