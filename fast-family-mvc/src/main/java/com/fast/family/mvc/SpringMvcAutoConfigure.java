package com.fast.family.mvc;


import com.fast.family.mvc.filter.AccessLogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@EnableConfigurationProperties({SpringMvcProperties.class})
public class SpringMvcAutoConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "fast.family.mvc.accessLog",name = "enabled",havingValue = "true")
    public Filter accessLogFilter(){
        return new AccessLogFilter();
    }
}
