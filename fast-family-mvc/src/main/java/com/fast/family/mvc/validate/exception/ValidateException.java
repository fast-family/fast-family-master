package com.fast.family.mvc.validate.exception;

/**
 * @author 张顺
 * @version 1.0
 */
public class ValidateException extends RuntimeException{

    private String name;

    private String errMsg;


    public ValidateException() {
        super();
    }

    public ValidateException(String name,String message) {
        super(message);
        this.name = name;
        this.errMsg = message;
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    protected ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getName() {
        return name;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
