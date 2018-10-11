package com.fast.family.datasource.druid;

import com.fast.family.datasource.DataSourceProperties;
import lombok.Data;

import java.util.Properties;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/2-18:39
 */
@Data
public class DruidDataSourceProperties extends DataSourceProperties {

    private int initialSize = 0;
    private int maxActive = 8;
    private int minIdle = 0;
    private long maxWait = -1;
    private long timeBetweenEvictionRunsMillis = 60 * 1000L;
    private long minEvictableIdleTimeMillis = 1000L * 60L * 30L;
    private long maxEvictableIdleTimeMillis = 1000L * 60L * 60L * 7;
    private String validationQuery = "select 1";
    private int validationQueryTimeout = -1;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean testWhileIdle = true;
    private boolean poolPreparedStatements = false;
    private int maxOpenPreparedStatements = -1;
    private boolean sharePreparedStatements = false;
    private Properties connectionProperties;
    private String filters = "stat,wall";
}
