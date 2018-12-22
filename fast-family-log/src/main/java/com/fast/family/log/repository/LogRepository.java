package com.fast.family.log.repository;

import com.fast.family.log.AccessLogInfo;

/**
 * @author 张顺
 * @version 1.0
 */
public interface LogRepository {

    void save(AccessLogInfo accessLogInfo);

}
