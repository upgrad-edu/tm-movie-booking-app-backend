package com.upgrad.mtb.exceptions;

public class CityDetailsNotFoundException extends RuntimeException {
    public CityDetailsNotFoundException() {
    }

    public CityDetailsNotFoundException(String message) {
        super(message);
    }

    public CityDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public CityDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
