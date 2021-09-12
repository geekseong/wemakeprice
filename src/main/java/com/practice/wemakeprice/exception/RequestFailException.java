package com.practice.wemakeprice.exception;

public class RequestFailException extends RuntimeException {
    public RequestFailException(String message) {
        super(message);
    }
}
