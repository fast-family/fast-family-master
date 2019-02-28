package com.fast.family.commons;


import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Data
@EnableAutoConfiguration
@ConditionalOnProperty(prefix = "fast.family.common")
public class CommonProperties {

    private final SecuirtyConfiguration secuirty = new SecuirtyConfiguration();

    private final LdempotentConfiguration ldempotent = new LdempotentConfiguration();

    @Data
    public static class SecuirtyConfiguration{

        private final CorsConfiguration cors = new CorsConfiguration();

        @Data
        public static class CorsConfiguration{

            private String allowOrigin = "*";

            private String allowMethods = "POST,GET,OPTIONS,PUT,DELETE,HEAD";

            private String allowHeaders = "x-requested-with,content-type";


        }
    }


    @Data
    public static class LdempotentConfiguration{

        private int ttl = 5;

        private String ldempotentKey = "Ldempotent_Storage";

        private String ldempotentHeaderName = "Ldempotent_Token";

        private String ldempotentPrefix = "ldempotent_";
    }

}
