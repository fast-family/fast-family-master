package com.fast.family.third.poarty.miniapp;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;


/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "fast.family.wechat.miniapp", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(WechatMiniAppProperties.class)
public class WechatMiniAppAutoConfiguration {

    @Configuration
    public class MemoryWechatMiniAppAutoConfiguration {

        @Autowired
        private WechatMiniAppProperties properties;

        @Bean
        @ConditionalOnMissingBean
        public WxMaConfig maConfig() {
            WxMaInMemoryConfig config = new WxMaInMemoryConfig();
            config.setAppid(this.properties.getAppId());
            config.setSecret(this.properties.getSecret());
            Optional.ofNullable(this.properties.getToken()).ifPresent(config::setToken);
            Optional.ofNullable(this.properties.getAesKey()).ifPresent(config::setAesKey);
            config.setMsgDataFormat(Optional.ofNullable(this.properties.getMsgDataFormat()).orElse("JSON"));
            return config;
        }

        @Bean
        @ConditionalOnMissingBean
        public WxMaService wxMaService(WxMaConfig maConfig) {
            WxMaService service = new WxMaServiceImpl();
            service.setWxMaConfig(maConfig);
            return service;
        }
    }


}
