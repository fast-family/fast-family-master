package com.fast.family.log;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-21:46
 */
public interface AccessLogInterceptor {

    default void before(AccessLogInfo accessLogInfo){};

    default void after(AccessLogInfo accessLogInfo){};


}
