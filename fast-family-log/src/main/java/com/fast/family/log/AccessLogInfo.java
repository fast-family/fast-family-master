package com.fast.family.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-9:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccessLogInfo implements Serializable {

    private Map<String,String> requestParams;

    private Map<String,String> headerParams;

    private String applyName;

    private String methodName;

    private String methodType;

    private String desc;

    private String url;

    private String ip;

    private Object response;

    private long requestTime;

    private long responseTime;


    private Exception exception;

}
