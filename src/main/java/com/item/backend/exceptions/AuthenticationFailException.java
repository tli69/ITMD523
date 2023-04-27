package com.item.backend.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
