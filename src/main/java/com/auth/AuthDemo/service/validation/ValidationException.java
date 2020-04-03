package com.auth.AuthDemo.service.validation;

/**
 * Custom exception class for exceptions to be thrown if validation fails.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
