package com.fast.family.datasource.xa;

import com.fast.family.datasource.DataSourceProperties;
import lombok.Data;

import java.util.Properties;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class AtomikosDataSouceProperties {

    private int minPoolSize = 5;
    private int maxPoolSize = 200;
    private int borrowConnectionTimeout = 60;
    private int reapTimeout = 0;
    private int maxIdleTime = 60;
    private int maintenanceInterval = 60;
    private int defaultIsolationLevel = -1;
    private String xaDataSourceClassName = null;
    private int loginTimeout = 0;
    private String testQuery = null;
    private int maxLifetime = 0;
    private String uniqueResourceName = null;
    private Properties xaProperties = null;
    //初始化超时时间
    private int initTimeout = 10;

}
