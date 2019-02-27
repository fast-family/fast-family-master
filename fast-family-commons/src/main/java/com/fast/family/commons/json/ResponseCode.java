package com.fast.family.commons.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200,"成功"),
    SERVER_ERROR(500,"服务器异常"),
    LDEMPOTENT_ERROR(500_001,"请勿重复提交");

    private int code;

    private String message;
}
