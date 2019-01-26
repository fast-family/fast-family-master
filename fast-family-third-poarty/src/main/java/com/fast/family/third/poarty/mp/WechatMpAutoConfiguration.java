package com.fast.family.third.poarty.mp;



import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceOkHttpImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
@Configuration
@ConditionalOnClass(WxMpService.class)
@ConditionalOnProperty(prefix ="fast.family.wechat.mp",name = "enabled",havingValue = "true")
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpAutoConfiguration {

    @Configuration
//    @ConditionalOnMissingClass("org.redisson.api.RedissonClient")
    public class MemoryWechatMqAutoConfiguration{

        @Autowired
        private WechatMpProperties wechatMpProperties;

        @Bean
        @ConditionalOnMissingBean
        public WxMpConfigStorage wxMpConfigStorage(){
            WxMpInMemoryConfigStorage memoryConfigStorage = new WxMpInMemoryConfigStorage();
            memoryConfigStorage.setAccessToken(wechatMpProperties.getToken());
            memoryConfigStorage.setAesKey(wechatMpProperties.getAesKey());
            memoryConfigStorage.setAppId(wechatMpProperties.getAppId());
            memoryConfigStorage.setSecret(wechatMpProperties.getSecret());
            return memoryConfigStorage;
        }

        @Bean
        @ConditionalOnMissingBean
        public WxMpService wxMpService(){
            WxMpService wxMpService = new WxMpServiceOkHttpImpl();
            wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
            return wxMpService;
        }
    }

}
