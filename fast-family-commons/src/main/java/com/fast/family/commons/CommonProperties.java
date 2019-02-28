package com.fast.family.commons;


import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Data
@EnableAutoConfiguration
@ConditionalOnProperty(prefix = "fast.family.common")
public class CommonProperties {

    private  SecuirtyConfiguration secuirty = new SecuirtyConfiguration();

    @Data
    public static class SecuirtyConfiguration{

        private  CorsConfiguration cors = new CorsConfiguration();

        @Data
        public static class CorsConfiguration{

            private String allowOrigin = "*";

            private String allowMethods = "POST,GET,OPTIONS,PUT,DELETE,HEAD";

            private String allowHeaders = "x-requested-with,content-type";


        }
    }

}
