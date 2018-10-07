package com.fast.family.datasource;

import com.fast.family.datasource.context.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/3-11:00
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource
        implements DynamicDataSource,InitializingBean {

    @Autowired
    private DynamicDataSourceProperties properties;

    @Override
    protected DataSource determineTargetDataSource() {
        String dataSourceKey = DataSourceContextHolder.get();
        if (dataSourceKey != null && !dataSourceKey.equals("")){
            return DynamicDataSourceCacheUtils.get(dataSourceKey);
        } else {
            return DynamicDataSourceCacheUtils.get("master");
        }
    }



    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }

    @Override
    public void afterPropertiesSet() {
       this.setTargetDataSources(this.loadDataSource());
    }

    @Override
    public Map<Object,Object> loadDataSource() {
        Map<String,DynamicDataSourceProperties> map = properties.getDatasource();
        Iterator<Map.Entry<String, DynamicDataSourceProperties>> iterator = map.entrySet().iterator();

        Map<Object,Object> dataSourceMap = new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String,DynamicDataSourceProperties> entry = iterator.next();
            DataSource dataSource = DynamicDataSourceFatcory.createDateSource(entry.getValue());
            dataSourceMap.put(entry.getKey(),dataSource);
            DynamicDataSourceCacheUtils.put(entry.getKey(),dataSource);
        }
        return dataSourceMap;
    }
}
