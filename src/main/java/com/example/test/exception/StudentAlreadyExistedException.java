package com.example.test.exception;

public class StudentAlreadyExistedException extends ObjectNotFoundException {

    private static final int CODE = 1001;

    public StudentAlreadyExistedException() {
        super(CODE, "Student with such email is already Existed.");
    }

    public StudentAlreadyExistedException(String message) {
        super(CODE, message);
    }
}

