package com.upgrad.mtb.exceptions;

public class BookingFailedException extends Exception {
    public BookingFailedException() {
    }

    public BookingFailedException(String message) {
        super(message);
    }

    public BookingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingFailedException(Throwable cause) {
        super(cause);
    }

    public BookingFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
