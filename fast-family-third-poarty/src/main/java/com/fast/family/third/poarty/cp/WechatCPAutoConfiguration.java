package com.fast.family.third.poarty.cp;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WechatCPProperties.class})
public class WechatCPAutoConfiguration {
}
