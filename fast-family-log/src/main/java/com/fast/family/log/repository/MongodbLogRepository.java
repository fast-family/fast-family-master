package com.fast.family.log.repository;

import com.fast.family.log.AccessLogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
public class MongodbLogRepository implements LogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(AccessLogInfo accessLogInfo) {
        mongoTemplate.save(accessLogInfo);
    }
}
