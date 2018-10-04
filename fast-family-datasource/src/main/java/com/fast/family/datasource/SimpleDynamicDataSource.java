package com.fast.family.datasource;

import com.fast.family.datasource.druid.DruidDataSourceProperties;
import com.fast.family.datasource.exception.DynamicDataSourceException;
import com.fast.family.datasource.hikaricp.HikariCPProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/2-22:37
 */
@Slf4j
@Component
public class SimpleDynamicDataSource implements DynamicDataSource,InitializingBean{

    @Autowired
    private DynamicDataSourceProperties properties;

    @Override
    public Map<String, DataSource> loadDataSource() {
        Map<String,DynamicDataSourceProperties> map = properties.getDatasource();
        Iterator<Map.Entry<String, DynamicDataSourceProperties>> iterator = map.entrySet().iterator();
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String,DynamicDataSourceProperties> entry = iterator.next();
            DataSource dataSource = DynamicDataSourceFatcory.createDateSource(entry.getValue());
            dataSourceMap.put(entry.getKey(),dataSource);
        }
        return dataSourceMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.loadDataSource();
    }
}
