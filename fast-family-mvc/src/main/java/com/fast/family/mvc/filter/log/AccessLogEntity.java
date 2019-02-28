package com.fast.family.mvc.filter.log;

import lombok.Data;

@Data
public class AccessLogEntity {

    private RequestInfo requestInfo;

    private ResponseInfo responseInfo;

    @Data
    public static class RequestInfo{

        private String uri;

        private String method;

        private String params;

        private String client;

        private String session;

        private String user;

        private String headers;

        private String payload;
    }

    @Data
    public static class ResponseInfo{

        private String headers;

        private String payload;

        private int status;

        private int size;

        private long cost;

    }
}
