package com.fast.family.datasource;

import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class DataSourceProperties {

    private String username;
    private String password;
    private String url;
    private String driverClassName = null;
}
