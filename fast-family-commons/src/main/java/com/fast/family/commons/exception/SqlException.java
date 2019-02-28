package com.fast.family.commons.exception;

public class SqlException extends BaseException {


    public SqlException(int errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlException(Throwable cause) {
        super(cause);
    }

    public SqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
