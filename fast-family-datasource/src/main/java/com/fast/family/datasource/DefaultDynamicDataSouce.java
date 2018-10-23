package com.fast.family.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
@Slf4j
public class DefaultDynamicDataSouce implements DynamicDataSource{

    @Autowired
    private DynamicDataSourceProperties properties;

    @Override
    public Map<Object, Object> loadDataSource() {
        Class<? extends DataSource> type = properties.getType();
        DataSource result = (DataSource) BeanUtils.instantiate(type);
        Map<String,DynamicDataSourceProperties> map = properties.getDatasource();
        Iterator<Map.Entry<String, DynamicDataSourceProperties>> iterator = map.entrySet().iterator();

        Map<Object,Object> dataSourceMap = new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String,DynamicDataSourceProperties> entry = iterator.next();
            DataSource dataSource = DynamicDataSourceFatcory.createDateSource(entry.getValue(),result);
            dataSourceMap.put(entry.getKey(),dataSource);
            DynamicDataSourceCacheUtils.put(entry.getKey(),dataSource);
        }
        return dataSourceMap;
    }
}
