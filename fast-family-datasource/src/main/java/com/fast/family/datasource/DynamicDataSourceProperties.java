package com.fast.family.datasource;


import com.fast.family.datasource.druid.DruidDataSourceProperties;
import com.fast.family.datasource.xa.AtomikosDataSouceProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
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

    private Class<? extends DataSource> type;

    private DruidDataSourceProperties druid;

    private AtomikosDataSouceProperties atomikos = new AtomikosDataSouceProperties();

    private Map<String,DynamicDataSourceProperties> datasource = new HashMap<>();

}
