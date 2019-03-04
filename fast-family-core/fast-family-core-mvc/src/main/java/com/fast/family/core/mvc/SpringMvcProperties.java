package com.fast.family.core.mvc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "fast.family.mvc")
public class SpringMvcProperties {

    private boolean isIncludeRequest = true;

    private int requestBodyLength = 8192;

    private int responseBodyLength = 8192;
}
