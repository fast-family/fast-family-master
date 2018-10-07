package com.fast.family.datasource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/7-14:43
 */
public class DynamicDataSourceCacheUtils {

    private static final Map<String,DataSource> params = new ConcurrentHashMap<>();



    public static void put(String key,DataSource dataSource){
        params.put(key,dataSource);
    }

    public static DataSource get(String key){
        return params.get(key);
    }
}
