package com.upgrad.exceptions;

public class TheatreNotFoundException extends Exception {
    public TheatreNotFoundException() {
    }

    public TheatreNotFoundException(String message) {
        super(message);
    }

    public TheatreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TheatreNotFoundException(Throwable cause) {
        super(cause);
    }

    public TheatreNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
