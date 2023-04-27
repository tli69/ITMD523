package com.item.backend.exceptions;

public class CustomException extends IllegalArgumentException {
    public CustomException(String msg) {
        super(msg);
    }
}
