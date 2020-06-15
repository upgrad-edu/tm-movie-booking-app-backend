package com.upgrad.mtb.exceptions;

public class LanguageDetailsNotFoundException extends Exception {
    public LanguageDetailsNotFoundException() {
    }

    public LanguageDetailsNotFoundException(String message) {
        super(message);
    }

    public LanguageDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LanguageDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public LanguageDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
