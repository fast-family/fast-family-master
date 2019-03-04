package com.fast.family.core.tomcat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fast.family.tomcat.shutdown")
@Data
public class TomcatGracefulShutdownProperties {

    //单位秒
    private Integer waitTime = 30;

}
