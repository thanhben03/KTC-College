package com.likelion.manage_book.Model;

public class ErrorHandler extends RuntimeException {
    public ErrorHandler(String msg) {
        super(msg);
    }
}
