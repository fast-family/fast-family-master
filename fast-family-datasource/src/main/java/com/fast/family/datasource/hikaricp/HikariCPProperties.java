package com.fast.family.datasource.hikaricp;

import com.fast.family.datasource.DataSourceProperties;
import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/2-18:36
 */
@Data
public class HikariCPProperties extends DataSourceProperties {


    //配置根据  https://github.com/brettwooldridge/HikariCP  文档来的
    private boolean autoCommit = true;
    private int connectionTimeout = 30000;
    private int idleTimeout = 600000;
    private int maxLifetime = 1800000;
    private int maximumPoolSize = 10;
    private int minimumIdle = 10;
    private int initializationFailTimeout = 1;
    private boolean isolateInternalQueries = false;
    private boolean allowPoolSuspension = false;
    private boolean readOnly = false;
    private boolean registerMbeans = false;
    private int validationTimeout = 5000;
    private int leakDetectionThreshold = 0;
}
