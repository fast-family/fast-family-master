package com.fast.family.datasource;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/11-14:42
 */
public enum  DatabaseType {

    mysql("com.mysql.jdbc.Driver","com.mysql.jdbc.jdbc2.optional.MysqlXADataSource","select 1");


    DatabaseType(String driverClassName, String xaDataSourceClassName,String testQuery) {
        this.testQuery = testQuery;
        this.driverClassName = driverClassName;
        this.xaDataSourceClassName = xaDataSourceClassName;
    }

    private String testQuery;

    private String driverClassName;

    private String xaDataSourceClassName;
}
