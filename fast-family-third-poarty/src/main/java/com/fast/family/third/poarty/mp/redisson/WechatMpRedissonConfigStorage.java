package com.fast.family.third.poarty.mp.redisson;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.redisson.api.RedissonClient;

@Slf4j
public class WechatMpRedissonConfigStorage extends WxMpInMemoryConfigStorage {

    private RedissonClient redissonClient;

    public WechatMpRedissonConfigStorage(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private static final String MQ_REDISSON_CONFIG_STORAGE = "mqRedissonConfigStorage";

    private static final String MQ_ACCESS_TOKEN = "mqAccessToken";

    private static final String MQ_AES_KEY = "mqAesKey";

    private static final String MQ_APP_ID = "mqAppId";

    private static final String MQ_SECRET = "mqSecret";

    @Override
    public void setAccessToken(String accessToken) {
        redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).put(MQ_ACCESS_TOKEN,accessToken);
    }

    @Override
    public String getAccessToken() {
        return (String) redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).get(MQ_ACCESS_TOKEN);
    }

    @Override
    public String getAesKey() {
        return (String) redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).get(MQ_AES_KEY);
    }

    @Override
    public void setAesKey(String aesKey) {
        redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).put(MQ_AES_KEY,aesKey);
    }

    @Override
    public String getAppId() {
        return (String) redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).get(MQ_APP_ID);
    }

    @Override
    public void setAppId(String appId) {
        redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).put(MQ_APP_ID,appId);
    }

    @Override
    public String getSecret() {
        return (String) redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).get(MQ_SECRET);
    }

    @Override
    public void setSecret(String secret) {
        redissonClient.getMap(MQ_REDISSON_CONFIG_STORAGE).put(MQ_SECRET,secret);
    }
}

