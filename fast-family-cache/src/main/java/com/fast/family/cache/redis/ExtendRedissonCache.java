package com.fast.family.cache.redis;

import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonCache;

import java.util.concurrent.Callable;

public class ExtendRedissonCache extends RedissonCache {

    public ExtendRedissonCache(RMapCache<Object, Object> mapCache, CacheConfig config, boolean allowNullValues) {
        super(mapCache, config, allowNullValues);
    }

    public ExtendRedissonCache(RMap<Object, Object> map, boolean allowNullValues) {
        super(map, allowNullValues);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public RMap<?, ?> getNativeCache() {
        return super.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        return super.get(key);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return super.get(key, type);
    }

    @Override
    public void put(Object key, Object value) {
        super.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return super.putIfAbsent(key, value);
    }

    @Override
    public void evict(Object key) {
        super.evict(key);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return super.get(key, valueLoader);
    }
}
