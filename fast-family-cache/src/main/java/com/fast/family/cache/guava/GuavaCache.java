package com.fast.family.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCache {


    private Cache<String,Object> cache = CacheBuilder.newBuilder()
            .build();


}
