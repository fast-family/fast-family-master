package com.fast.family.datasource.aop;

import com.fast.family.datasource.annotation.DataSourceAnnotation;
import com.fast.family.datasource.context.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/1-21:06
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class DynamicDataSourceAspect {


    @Pointcut("@within(com.fast.family.datasource.annotation.DataSourceAnnotation) ||" +
            " @annotation(com.fast.family.datasource.annotation.DataSourceAnnotation)")
    public void pointcut(){};


    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DataSourceAnnotation dataSourceAnnotation =
                method.getAnnotation(DataSourceAnnotation.class);
        if (dataSourceAnnotation == null){
            dataSourceAnnotation = joinPoint.getTarget().getClass()
                    .getAnnotation(DataSourceAnnotation.class);
            if (dataSourceAnnotation == null){
                return;
            }
        }
        String dataSourceKey = dataSourceAnnotation.name();
        if (dataSourceKey != null){
            DataSourceContextHolder.add(dataSourceKey);
        }
    }

    public void after(JoinPoint joinPoint){
        DataSourceContextHolder.clear();
    }
}
