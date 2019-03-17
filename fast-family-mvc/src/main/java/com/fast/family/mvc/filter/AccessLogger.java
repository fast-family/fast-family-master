package com.fast.family.core.mvc.filter;


import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.commons.utils.WebUtils;
import com.fast.family.core.mvc.SpringMvcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class AccessLogger {

    private AccessLogEntity.RequestInfo requestInfo = new AccessLogEntity.RequestInfo();

    private AccessLogEntity.ResponseInfo responseInfo = new AccessLogEntity.ResponseInfo();

    private SpringMvcProperties mvcProperties;

    public AccessLogger(SpringMvcProperties mvcProperties) {
        this.mvcProperties = mvcProperties;
    }

    public void appendRequestCommonMessage(HttpServletRequest request){


        requestInfo.setUri(request.getRequestURI());
        requestInfo.setMethod(request.getMethod());
        if (mvcProperties.isIncludeRequest()){
            Map<String,String[]> params = request.getParameterMap();
            if (params != null && !params.isEmpty()){
                requestInfo.setParams(getParams(params));
            }

            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)){
                requestInfo.setClient(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null){
                requestInfo.setSession(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null){
                requestInfo.setUser(user);
            }
        }

    }

    public void appendRequestDetailMessage(boolean includeRequest,HttpServletRequest request){

        if (includeRequest && WebUtils.isNormalRequest(request)){
            requestInfo.setHeaders(new ServletServerHttpRequest(request).getHeaders().toString());
            ContentCachingRequestWrapper wrapper = WebUtils
                    .getNativeRequest(request,ContentCachingRequestWrapper.class);
            if (wrapper != null){
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0){
                    int length = Math.min(buf.length,mvcProperties.getRequestBodyLength());
                    String payload;
                    try {
                        payload = new String(buf,0,length,wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        payload = "[unknown]";
                    }
                    requestInfo.setPayload(payload);
                }
            }
        }

    }

    public void appendResponseCommonMessage(ContentCachingResponseWrapper response, long cost){
        responseInfo.setSize(response.getContentSize());
        responseInfo.setStatus(response.getStatusCode());
        responseInfo.setCost(cost);
    }

    public void appendResponseDetailMessage(ContentCachingResponseWrapper response){
        StringBuilder msg = new StringBuilder();
        String contentType = response.getContentType();
        responseInfo.setHeaders(new ServletServerHttpResponse(response).getHeaders().toString());
        Optional.ofNullable(contentType).filter(c -> c.startsWith("application/json")).ifPresent(c -> {
            byte[] buf = response.getContentAsByteArray();
            if (buf.length > 0){
                int length = Math.min(buf.length,mvcProperties.getResponseBodyLength());
                String payload;

                try {
                    payload = new String(buf,0,length,response.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    payload = "[unknown]";
                }
                msg.append(";payload=").append(payload);
                responseInfo.setPayload(payload);
            }
        });

    }





    public void printLog(){
        log.info("request 信息:" + GsonUtils.toJson(requestInfo, AccessLogEntity.RequestInfo.class));
        log.info("response 信息:" + GsonUtils.toJson(responseInfo, AccessLogEntity.ResponseInfo.class));
    }




    private String getParams(final Map<String,String[]> params){
        List<String> parts = new ArrayList<>();
        params.forEach((k,v) -> {
            String param = k + "=[" + Arrays.stream(v).map(String::valueOf)
                    .collect(Collectors.joining(",")) + "]";
            parts.add(param);
        });
        return parts.stream().collect(Collectors.joining(","));
    }
}
