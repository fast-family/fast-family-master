package com.fast.family.security.code;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    private static final String VALIDATE_CODE = "validateCode";

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void save(String key, ValidateCode validateCode) {
        RMap<String, ValidateCode> rMap = redissonClient.getMap(VALIDATE_CODE);
        rMap.put(key, validateCode);
    }

    @Override
    public ValidateCode get(String key) {
        RMap<String, ValidateCode> rMap = redissonClient.getMap(VALIDATE_CODE);
        return rMap.get(key);
    }

    @Override
    public void remove(String key) {
        RMap<String, ValidateCode> rMap = redissonClient.getMap(VALIDATE_CODE);
        rMap.fastRemove(key);
    }
}
