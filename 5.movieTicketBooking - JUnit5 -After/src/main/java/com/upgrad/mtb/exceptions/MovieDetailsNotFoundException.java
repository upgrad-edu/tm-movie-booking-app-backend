package com.upgrad.mtb.exceptions;

public class MovieDetailsNotFoundException extends Exception{
    public MovieDetailsNotFoundException() {
    }

    public MovieDetailsNotFoundException(String message) {
        super(message);
    }

    public MovieDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public MovieDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
