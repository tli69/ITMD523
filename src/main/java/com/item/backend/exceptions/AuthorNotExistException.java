package com.item.backend.exceptions;

public class AuthorNotExistException extends IllegalArgumentException {
    public AuthorNotExistException(String msg) {
        super(msg);
    }
}