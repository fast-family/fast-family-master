package com.fast.family.log;

/**
 * @author 张顺
 * @version 1.0
 */
public interface AccessLogInterceptor {

    default void before(AccessLogInfo accessLogInfo){};

    default void after(AccessLogInfo accessLogInfo){};


}
