package com.item.backend.exceptions;

public class ItemNotExistException extends IllegalArgumentException {
    public ItemNotExistException(String msg) {
        super(msg);
    }
}
