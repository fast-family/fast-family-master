package com.fast.family.log.aop;

import com.fast.family.commons.utils.WebUtils;
import com.fast.family.log.AccessLogInfo;
import com.fast.family.log.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-9:53
 */
@Slf4j
public class AccessLogPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        LogAnnotation log = method.getAnnotation(LogAnnotation.class);
        if (log != null){
            return true;
        }
        return false;
    }






}
