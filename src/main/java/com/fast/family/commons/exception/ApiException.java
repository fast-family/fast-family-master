package com.fast.family.commons.exception;

public class ApiException extends BaseException{

    public ApiException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
