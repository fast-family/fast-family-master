package com.fast.family.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.fast.family.datasource.druid.DruidDataSourceProperties;
import com.fast.family.datasource.exception.DynamicDataSourceException;
import com.fast.family.datasource.hikaricp.HikariCPProperties;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/3-11:28
 */
public class DynamicDataSourceFatcory {

    public static DataSource createDateSource(DynamicDataSourceProperties properties){
        if (properties.getDruid() != null){
            return createDruidDataSource(properties.getDruid());
        } else if (properties.getHikaricp() != null){
            return createHikariCPDataSource(properties.getHikaricp());
        }
        throw new DynamicDataSourceException("数据源创建失败");
    }


    private static DataSource createHikariCPDataSource(HikariCPProperties properties){
        HikariDataSource hikariDataSource = new HikariDataSource();
        properties.setUsername(properties.getUsername());
        properties.setPassword(properties.getPassword());
        properties.setDriverClassName(properties.getDriverClassName());
        properties.setUrl(properties.getUrl());
        properties.setConnectionTimeout(properties.getConnectionTimeout());
        properties.setIdleTimeout(properties.getIdleTimeout());
        properties.setMaximumPoolSize(properties.getMaximumPoolSize());
        properties.setMaxLifetime(properties.getMaxLifetime());
        properties.setMinimumIdle(properties.getMinimumIdle());
        properties.setInitializationFailTimeout(properties.getInitializationFailTimeout());
        properties.setIsolateInternalQueries(properties.isIsolateInternalQueries());
        properties.setAllowPoolSuspension(properties.isAllowPoolSuspension());
        properties.setReadOnly(properties.isReadOnly());
        properties.setRegisterMbeans(properties.isRegisterMbeans());
        properties.setValidationTimeout(properties.getValidationTimeout());
        properties.setLeakDetectionThreshold(properties.getLeakDetectionThreshold());
        return hikariDataSource;
    }

    private static DataSource createDruidDataSource(DruidDataSourceProperties properties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());
        druidDataSource.setConnectProperties(properties.getConnectionProperties());
        try {
            druidDataSource.setFilters(properties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            throw new DynamicDataSourceException("创建数据源失败",e);
        }
        return druidDataSource;
    }

}
