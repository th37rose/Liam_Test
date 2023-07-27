package com.example.test.exception;

public class StudentNotFoundException extends ObjectNotFoundException {

    private static final int CODE = 1000;

    public StudentNotFoundException() {
        super(CODE, "Student with such id was not found.");
    }

    public StudentNotFoundException(String message) {
        super(CODE, message);
    }
}
