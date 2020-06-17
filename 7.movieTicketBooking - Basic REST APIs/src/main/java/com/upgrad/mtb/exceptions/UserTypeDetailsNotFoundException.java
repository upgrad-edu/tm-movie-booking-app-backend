package com.upgrad.mtb.exceptions;

public class UserTypeDetailsNotFoundException extends Exception {
    public UserTypeDetailsNotFoundException() {
    }

    public UserTypeDetailsNotFoundException(String message) {
        super(message);
    }

    public UserTypeDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTypeDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserTypeDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
