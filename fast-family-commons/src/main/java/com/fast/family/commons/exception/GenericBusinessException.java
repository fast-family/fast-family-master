package com.fast.family.commons.exception;

public class GenericBusinessException extends BaseException {

    public GenericBusinessException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public GenericBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBusinessException(Throwable cause) {
        super(cause);
    }

    public GenericBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
