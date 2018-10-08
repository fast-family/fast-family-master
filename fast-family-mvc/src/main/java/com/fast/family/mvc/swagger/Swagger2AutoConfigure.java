package com.fast.family.mvc.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@EnableSwagger2
@Configuration
@ConditionalOnClass(Docket.class)
@ConditionalOnProperty(value = "fast.family.swagger2.enabled",matchIfMissing = true)
@EnableConfigurationProperties({Swagger2ApiInfoProperties.class,Swagger2RestApiProperties.class})
public class Swagger2AutoConfigure {


    @Bean
    public Docket createRestApi(Swagger2RestApiProperties swagger2RestApiProperties, ApiInfo apiInfo){ ;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(Optional.ofNullable(swagger2RestApiProperties.getBackPackage())
                    .map(RequestHandlerSelectors::basePackage).orElse(RequestHandlerSelectors.any()))
                .paths(Optional.ofNullable(swagger2RestApiProperties.getPaths()).map(PathSelectors::regex)
                    .orElse(PathSelectors.any()))
                .build();
    }

    @Bean(name = "swagger2ApiInfo")
    public ApiInfo apiInfo(Swagger2ApiInfoProperties apiInfoProperties){
        return new ApiInfoBuilder()
                .title(apiInfoProperties.getTitle())
                .description(apiInfoProperties.getDescription())
                .termsOfServiceUrl(apiInfoProperties.getTermsOfServiceUrl())
                .version(apiInfoProperties.getVersion())
                .contact(new Contact(apiInfoProperties.getContactName(),
                        apiInfoProperties.getContactUrl(),apiInfoProperties.getContactEmail()))
                .license(apiInfoProperties.getLicense())
                .licenseUrl(apiInfoProperties.getLicenseUrl())
                .build();
    }

}
