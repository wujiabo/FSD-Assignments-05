package com.wujiabo.fsd.exception;

public class FSDException extends RuntimeException {

    public FSDException() {
    }

    public FSDException(String message) {
        super(message);
    }

    public FSDException(String message, Throwable cause) {
        super(message, cause);
    }

    public FSDException(Throwable cause) {
        super(cause);
    }

    public FSDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
