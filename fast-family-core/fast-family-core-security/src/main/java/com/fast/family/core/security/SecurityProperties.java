package com.fast.family.core.security;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@Data
@ConfigurationProperties(prefix = "fast.family.security")
public class SecurityProperties {

    private String[] excludeIps;

    @Getter
    private final CorsConfiguration cors = new CorsConfiguration();

    @Getter
    private final Jwt jwt = new Jwt();

    @Getter
    private final MiniApp miniApp = new MiniApp();

    @Data
    public static class Jwt {

        private String[] excludeUrls;

        private String authoritiesKey = "fast-family-auth-key";

        private String secret = "fast-family-token-secret";

        private long tokenValidityInseconds = 1800; //毫秒

        private long tokenValidityInSecondsForRememberMe = 2592000; //毫秒
    }

    @Data
    public static class MiniApp {

        private String miniAppUrl;

        private String httpMethod;

    }


}
