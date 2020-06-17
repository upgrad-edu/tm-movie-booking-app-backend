package com.upgrad.mtb.exceptions;

public class TheatreDetailsNotFoundException extends Exception {

    public TheatreDetailsNotFoundException() {
    }

    public TheatreDetailsNotFoundException(String message) {
        super(message);
    }

    public TheatreDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TheatreDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public TheatreDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
