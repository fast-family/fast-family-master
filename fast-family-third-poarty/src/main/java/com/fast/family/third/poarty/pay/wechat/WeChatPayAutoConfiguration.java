package com.fast.family.third.poarty.pay.wechat;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@ConditionalOnClass(WxPayService.class)
@ConditionalOnProperty(prefix = "fast.family.wechat.pay", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(WeChatPayProperties.class)
public class WeChatPayAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxPayService(WeChatPayProperties payProperties) {
        log.debug("初始化微信支付");
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(payProperties.getAppId());
        wxPayConfig.setMchId(payProperties.getMchId());
        wxPayConfig.setMchKey(payProperties.getMchKey());
        wxPayConfig.setSubAppId(payProperties.getSubAppId());
        wxPayConfig.setSubMchId(payProperties.getSubMchId());
        wxPayConfig.setKeyPath(payProperties.getKeyPath());
        //是否制定沙箱环境
        wxPayConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }

}
