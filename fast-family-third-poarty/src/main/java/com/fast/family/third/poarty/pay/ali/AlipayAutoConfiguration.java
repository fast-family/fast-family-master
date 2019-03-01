package com.fast.family.third.poarty.pay.ali;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.fast.family.commons.constant.CommonStant;
import com.fast.family.third.poarty.pay.ali.service.AlipayAppService;
import com.fast.family.third.poarty.pay.ali.service.AlipayService;
import com.fast.family.third.poarty.pay.ali.service.AlipayWapService;
import com.fast.family.third.poarty.pay.ali.service.impl.AlipayAppServiceImpl;
import com.fast.family.third.poarty.pay.ali.service.impl.AlipayServiceImpl;
import com.fast.family.third.poarty.pay.ali.service.impl.AlipayWapServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(AlipayClient.class)
@ConditionalOnProperty(prefix = CommonStant.PROPERTIS_PREFIX + "ali.pay", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({AlipayProperties.class})
public class AlipayAutoConfiguration {

    @Autowired
    private AlipayProperties aliPayProperties;

    @ConditionalOnMissingBean
    @Bean
    public AlipayClient alipayClient() {
        log.debug("初始化支付宝支付");
        return new DefaultAlipayClient(aliPayProperties.getGatewayUrl(), aliPayProperties.getAppId(),
                aliPayProperties.getMerchantPrivateKey(),
                aliPayProperties.getFormat(),
                aliPayProperties.getCharset(),
                aliPayProperties.getAlipayPublicKey(), aliPayProperties.getSignType());
    }

    @Bean
    public AlipayService alipayService() {
        return new AlipayServiceImpl(aliPayProperties, alipayClient());
    }

    @Bean
    public AlipayWapService alipayWapService() {
        return new AlipayWapServiceImpl(aliPayProperties, alipayClient());
    }

    @Bean
    public AlipayAppService alipayAppService() {
        return new AlipayAppServiceImpl(aliPayProperties, alipayClient());
    }
}
