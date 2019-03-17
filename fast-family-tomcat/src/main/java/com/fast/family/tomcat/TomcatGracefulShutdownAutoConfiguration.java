package com.fast.family.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "fast.family.tomcat.shutdown.enabled", havingValue = "true")
@ConditionalOnBean(TomcatServletWebServerFactory.class)
@ConditionalOnClass({Servlet.class, Tomcat.class})
@EnableConfigurationProperties(TomcatGracefulShutdownProperties.class)
public class TomcatGracefulShutdownAutoConfiguration {

    private final TomcatGracefulShutdownProperties tomcatGracefulShutdownProperties;

    public TomcatGracefulShutdownAutoConfiguration(TomcatGracefulShutdownProperties tomcatGracefulShutdownProperties) {
        this.tomcatGracefulShutdownProperties = tomcatGracefulShutdownProperties;
    }

    @Bean
    public TomcatGracefulShutdown tomcatGracefulShutdown() {
        return new TomcatGracefulShutdown(tomcatGracefulShutdownProperties);
    }

    @Bean
    public WebServerFactoryCustomizer tomcatServletWebServerFactory() {
        return server -> {
            if (server instanceof TomcatServletWebServerFactory) {
                ((TomcatServletWebServerFactory) server).addConnectorCustomizers(tomcatGracefulShutdown());
            }
        };
    }

}
