package com.fast.family.commons.exception;

public class NoLoginException extends BaseException {


    public NoLoginException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public NoLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLoginException(Throwable cause) {
        super(cause);
    }

    public NoLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
