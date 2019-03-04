package com.fast.family.core.swagger;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fast.family.swagger2.apiinfo")
public class Swagger2ApiInfoProperties {


    private String title = "";

    private String description = "";

    private String version = "";

    private String termsOfServiceUrl = "";

    private String contactName = "";

    private String contactUrl = "";

    private String contactEmail = "";

    private String license = "";

    private String licenseUrl = "";
}
