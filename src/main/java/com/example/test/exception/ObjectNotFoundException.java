package com.example.test.exception;

public class ObjectNotFoundException extends CustomException {

    public ObjectNotFoundException(int code, String message) {
        super(code, message);
    }
}
