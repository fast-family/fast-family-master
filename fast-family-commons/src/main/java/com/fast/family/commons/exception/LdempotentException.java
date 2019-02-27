package com.fast.family.commons.exception;

public class LdempotentException extends BaseException{

    public LdempotentException(String message) {
        super(message);
    }

    public LdempotentException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public LdempotentException(String message, int errCode, String errMessage) {
        super(message, errCode, errMessage);
    }

    public LdempotentException(String message, Throwable cause) {
        super(message, cause);
    }

    public LdempotentException(Throwable cause) {
        super(cause);
    }

    public LdempotentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
