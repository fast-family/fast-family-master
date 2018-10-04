package com.fast.family.datasource;

import com.fast.family.datasource.druid.DruidDataSourceProperties;
import com.fast.family.datasource.hikaricp.HikariCPProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/1-21:04
 */
@Data
@ConfigurationProperties(prefix = "fast.family")
public class DynamicDataSourceProperties {

    private final HikariCPProperties hikaricp = new HikariCPProperties();

    private final DruidDataSourceProperties druid = new DruidDataSourceProperties();

    private final Map<String,DynamicDataSourceProperties> datasource = new HashMap<>();

}
