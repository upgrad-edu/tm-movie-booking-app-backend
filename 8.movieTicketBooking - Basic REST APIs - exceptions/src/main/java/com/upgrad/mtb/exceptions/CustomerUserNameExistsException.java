package com.upgrad.mtb.exceptions;

public class CustomerUserNameExistsException extends  Exception {
    public CustomerUserNameExistsException() {
    }

    public CustomerUserNameExistsException(String message) {
        super(message);
    }

    public CustomerUserNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerUserNameExistsException(Throwable cause) {
        super(cause);
    }

    public CustomerUserNameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
