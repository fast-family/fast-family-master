package com.fast.family.third.poarty.miniapp.redisson;

import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

@Slf4j
public class WechatMaRedissonConfigStorage extends WxMaInMemoryConfig {

    private RedissonClient redissonClient;

    private static final String MA_REDISSON_CONFIG_STORAGE = "maRedissonConfigStorage";

    private static final String MA_AES_KEY = "maAesKey";

    private static final String MA_TOKEN_KEY = "maTokenKey";

    private static final String MA_APPID_KEY = "maAppIdKey";

    private static final String MA_SECRET_KEY = "maSecretKey";

    public WechatMaRedissonConfigStorage(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public String getAesKey() {
        return (String) redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).get(MA_AES_KEY);
    }

    @Override
    public void setAesKey(String aesKey) {
        redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).put(MA_AES_KEY,aesKey);
    }

    @Override
    public String getToken() {
        return (String) redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).get(MA_TOKEN_KEY);
    }

    @Override
    public void setToken(String token) {
        redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).put(MA_TOKEN_KEY,token);
    }

    @Override
    public String getAppid() {
        return (String) redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).get(MA_APPID_KEY);
    }

    @Override
    public void setAppid(String appid) {
        redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).put(MA_APPID_KEY,appid);
    }

    @Override
    public String getSecret() {
        return (String) redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).get(MA_SECRET_KEY);
    }

    @Override
    public void setSecret(String secret) {
        redissonClient.getMap(MA_REDISSON_CONFIG_STORAGE).put(MA_SECRET_KEY,secret);
    }
}
