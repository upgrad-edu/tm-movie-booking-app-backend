package com.upgrad.mtb.exceptions;

public class StatusDetailsNotFoundException extends Exception {
    public StatusDetailsNotFoundException() {
    }

    public StatusDetailsNotFoundException(String message) {
        super(message);
    }

    public StatusDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public StatusDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
