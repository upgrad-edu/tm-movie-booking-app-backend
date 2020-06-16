package com.upgrad.mtb.exceptions;

public class CustomerDetailsNotFoundException extends Exception {
    public CustomerDetailsNotFoundException() {
    }

    public CustomerDetailsNotFoundException(String message) {
        super(message);
    }

    public CustomerDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public CustomerDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
