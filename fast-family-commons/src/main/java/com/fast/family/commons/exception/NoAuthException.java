package com.fast.family.commons.exception;

public class NoAuthException extends BaseException{

    public NoAuthException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public NoAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthException(Throwable cause) {
        super(cause);
    }

    public NoAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
