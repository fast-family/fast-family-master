package com.fast.family.third.poarty.pay.ali;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "fast.family.ali.pay")
public class AlipayProperties {

    //应用ID
    private String appId;

    //公钥key
    private String alipayPublicKey;

    //PKCS8格式RSA2私钥
    private String merchantPrivateKey;

    //服务端异步回调地址
    private String notifyUrl;

    //页面跳转通知路径
    private String returnUrl;

    private String format;

    //签名方式
    private String signType;

    //字符编码格式
    private String charset;

    //支付网关路径
    private String gatewayUrl;

}
