package com.fast.family.log.aop;

import com.fast.family.commons.utils.WebUtils;
import com.fast.family.log.AccessLogInfo;
import com.fast.family.log.AccessLogInterceptor;
import com.fast.family.log.annotation.LogAnnotation;
import com.fast.family.log.disruptor.LogEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class AccessLogMethodInterceptor implements MethodInterceptor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private LogEventPublisher logEventPublisher;

    private final List<AccessLogInterceptor> logInterceptors = new LinkedList<>();

    public void add(AccessLogInterceptor accessLogInterceptor) {
        logInterceptors.add(accessLogInterceptor);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        LogAnnotation log = method.getAnnotation(LogAnnotation.class);
        Object result = null;
        AccessLogInfo accessLogInfo = createAccessLogInfo(methodInvocation);
        accessLogInfo.setMethodType(log.methodType());
        accessLogInfo.setMethodName(log.methodName());
        accessLogInfo.setDesc(log.desc());
        logInterceptors.forEach(logInterceptor -> logInterceptor.before(accessLogInfo));
        try {
            result = methodInvocation.proceed();
            accessLogInfo.setResponse(result);
        } catch (Exception e) {
            accessLogInfo.setException(e);
            throw e;
        } finally {
            accessLogInfo.setResponseTime(System.currentTimeMillis());
            logInterceptors.forEach(logInterceptor -> logInterceptor.after(accessLogInfo));
            logEventPublisher.publishEvent(accessLogInfo);
        }
        return result;
    }

    private AccessLogInfo createAccessLogInfo(MethodInvocation methodInvocation) {
        AccessLogInfo accessLogInfo = new AccessLogInfo();
        HttpServletRequest request = WebUtils.getHttpServletRequest();
        accessLogInfo.setUrl(request.getRequestURI());
        accessLogInfo.setIp(WebUtils.getClientIP(request));
        accessLogInfo.setRequestTime(System.currentTimeMillis());
        accessLogInfo.setHeaderParams(WebUtils.getRequestHeaders(request));
        accessLogInfo.setRequestParams(WebUtils.getRequestParameters(request));
        accessLogInfo.setApplyName(applicationName);
        //获取请求参数
        methodInvocation.getArguments();
        return accessLogInfo;
    }

}
