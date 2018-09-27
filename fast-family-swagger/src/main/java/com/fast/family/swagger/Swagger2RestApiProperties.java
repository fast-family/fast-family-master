package com.fast.family.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fast.family.swagger2.restapi")
public class Swagger2RestApiProperties {

    private String backPackage;

    private String paths;


}
