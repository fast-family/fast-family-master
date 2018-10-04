package com.fast.family.datasource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/1-21:06
 */
@Configuration
public class DynamicDataSourceAutoConfigure {

    @Bean
    public DynamicDataSource dynamicDataSource(){
        return new SimpleDynamicDataSource();
    }
}
