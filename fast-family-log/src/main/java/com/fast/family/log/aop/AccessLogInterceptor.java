package com.fast.family.log.aop;

import com.fast.family.commons.utils.WebUtils;
import com.fast.family.log.AccessLogInfo;
import com.fast.family.log.event.AccessLogAfterEvent;
import com.fast.family.log.event.AccessLogBeforeEvent;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-9:53
 */
@Component
@Slf4j
public class AccessLogInterceptor implements MethodInterceptor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        AccessLogInfo accessLogInfo = createAccessLogInfo(methodInvocation);
        try {
            applicationEventPublisher.publishEvent(new AccessLogBeforeEvent(null));
            methodInvocation.proceed();
            accessLogInfo.setResponseTime(System.currentTimeMillis());
        } catch (Exception e){
            accessLogInfo.setException(e);
            throw e;
        } finally {
            applicationEventPublisher.publishEvent(
                    new AccessLogAfterEvent(null));
        }
        return null;
    }

    private AccessLogInfo createAccessLogInfo(MethodInvocation methodInvocation){
        AccessLogInfo accessLogInfo = new AccessLogInfo();
        HttpServletRequest request = WebUtils.getHttpServletRequest();
        accessLogInfo.setUrl(request.getRequestURI());
        accessLogInfo.setIp(WebUtils.getClientIP(request));
        accessLogInfo.setRequestTime(System.currentTimeMillis());
        //获取请求参数
        methodInvocation.getArguments();
        return accessLogInfo;
    }
}
