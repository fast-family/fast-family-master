package com.fast.family.core.mvc.generic.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "fast.family.id")
public class IDProperties {

    private final SnowflakeId snowflakeId = new SnowflakeId();

    @Data
    public static class SnowflakeId {

        private long workId;

        private long dataCenterId;
    }
}
