package com.fast.family.datasource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/1-21:06
 */
@Configuration
@ConditionalOnClass(DynamicDataSourceProperties.class)
@EnableConfigurationProperties({DynamicDataSourceProperties.class})
public class DynamicDataSourceAutoConfigure {


//    @Bean
//    public DynamicDataSource defaultDynamicDataSource(){
//        return new DefaultDynamicDataSouce();
//    }

    @Bean
    public DynamicRoutingDataSource routingDataSource(){
        return new DynamicRoutingDataSource();
    }
}
