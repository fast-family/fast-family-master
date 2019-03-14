package com.fast.family.commons.exception;

/**
 * @author 张顺
 * @version 1.0
 */
public class AliSmsException extends BaseException {


    public AliSmsException(String message) {
        super(message);
    }

    public AliSmsException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public AliSmsException(String message, int errCode, String errMessage) {
        super(message, errCode, errMessage);
    }

    public AliSmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AliSmsException(Throwable cause) {
        super(cause);
    }

    public AliSmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
