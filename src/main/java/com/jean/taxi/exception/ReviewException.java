package com.jean.taxi.exception;

public class ReviewException extends Exception {
    public ReviewException() {
    }

    public ReviewException(String message) {
        super(message);
    }

    public ReviewException(String message, Throwable cause) {
        super(message, cause);
    }
}
