package com.fast.family.datasource.exception;

/**
 * @author 张顺
 * @version 1.0
 */
public class DynamicDataSourceException extends RuntimeException {


    public DynamicDataSourceException() {
        super();
    }

    public DynamicDataSourceException(String message) {
        super(message);
    }

    public DynamicDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DynamicDataSourceException(Throwable cause) {
        super(cause);
    }

    protected DynamicDataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
