package com.fast.family.datasource;

import com.fast.family.datasource.context.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/3-11:00
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
