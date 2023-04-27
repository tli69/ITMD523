package com.item.backend.exceptions;

public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException(String msg) {
        super(msg);
    }
}
