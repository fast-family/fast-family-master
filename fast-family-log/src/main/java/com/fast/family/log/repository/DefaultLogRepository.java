package com.fast.family.log.repository;

import com.fast.family.log.AccessLogInfo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
public class DefaultLogRepository implements LogRepository{

    private static Map<String,AccessLogInfo> params = new ConcurrentHashMap<>();

    @Override
    public void save(AccessLogInfo accessLogInfo) {
        params.put(UUID.randomUUID().toString().replace("-",""),accessLogInfo);
    }
}
