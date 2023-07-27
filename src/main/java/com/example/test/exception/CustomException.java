package com.example.test.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Application specific custom base exception. Code to represent each
 * application exception is introduced.
 *
 * @author mani
 */
@Getter
@NoArgsConstructor
public class CustomException extends Exception {

    private int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }
}
