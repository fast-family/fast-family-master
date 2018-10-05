package com.fast.family.commons.json;

import lombok.Getter;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/5-13:15
 */
@Getter
public enum  ResponseCode {

    SUCCESS(200,"success"),
    RUNTIME_ERROR(500,"runtime exception");



    private int code;

    private String msg;

    private String detailsMessage;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.detailsMessage = msg;
    }

    ResponseCode(int code, String msg, String detailsMessage) {
        this.code = code;
        this.msg = msg;
        this.detailsMessage = detailsMessage;
    }
}
