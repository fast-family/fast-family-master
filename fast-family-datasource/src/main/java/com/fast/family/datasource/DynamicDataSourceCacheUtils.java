package com.fast.family.datasource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 */
public class DynamicDataSourceCacheUtils {

    private static final Map<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<>();


    public static void put(String key, DataSource dataSource) {
        DATA_SOURCE_MAP.put(key, dataSource);
    }

    public static DataSource get(String key) {
        return DATA_SOURCE_MAP.get(key);
    }
}
