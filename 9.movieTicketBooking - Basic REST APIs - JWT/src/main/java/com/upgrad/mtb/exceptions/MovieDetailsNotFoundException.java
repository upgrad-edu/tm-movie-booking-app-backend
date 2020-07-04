package com.upgrad.mtb.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieDetailsNotFoundException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(MovieDetailsNotFoundException.class);

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
