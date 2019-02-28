package com.fast.family.commons.exception;


public class ValidateCodeException extends BaseException {


    public ValidateCodeException(String message) {
        super(message);
    }

    public ValidateCodeException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }


    public ValidateCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateCodeException(Throwable cause) {
        super(cause);
    }

    public ValidateCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
