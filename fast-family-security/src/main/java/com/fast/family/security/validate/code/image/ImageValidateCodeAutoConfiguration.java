//package com.fast.family.security.validate.code.image;
//
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//import com.google.code.kaptcha.util.Config;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
///**
// * @author 张顺
// * @version 1.0
// * @created 2018/9/27-23:30
// */
//@Configuration
//@ConditionalOnProperty(name = "fast.family.validate.code.image.enabled",havingValue = "true")
//@EnableConfigurationProperties({ImageValidateCodeProperties.class})
//public class ImageValidateCodeAutoConfiguration {
//
//    @Bean
//    public DefaultKaptcha defaultKaptcha(ImageValidateCodeProperties codeProperties){
//        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
//        Properties properties = new Properties();
//        properties.setProperty("kaptcha.border",codeProperties.getBorder().getBorder());
//        properties.setProperty("kaptcha.border.color",codeProperties.getBorder().getColor());
//        properties.setProperty("kaptcha.border.thickness",codeProperties.getBorder().getThickness());
//        properties.setProperty("kaptcha.producer.impl",codeProperties.getProducer().getImpl());
//        properties.setProperty("kaptcha.textproducer.impl",codeProperties.getTextProducer().getImpl());
//        properties.setProperty("kaptcha.textproducer.char.string",codeProperties.getTextProducer().getCharStr());
//        properties.setProperty("kaptcha.textproducer.char.length",codeProperties.getTextProducer().getCharLength());
//        properties.setProperty("kaptcha.textproducer.font.names",codeProperties.getTextProducer().getFontNames());
//        properties.setProperty("kaptcha.textproducer.font.size",codeProperties.getTextProducer().getFontSize());
//        properties.setProperty("kaptcha.textproducer.font.color",codeProperties.getTextProducer().getFontColor());
//        properties.setProperty("kaptcha.textproducer.char.space",codeProperties.getTextProducer().getCharSpace());
//        properties.setProperty("kaptcha.noise.impl",codeProperties.getNoise().getImpl());
//        properties.setProperty("kaptcha.noise.color",codeProperties.getNoise().getColor());
//        properties.setProperty("kaptcha.obscurificator.impl",codeProperties.getObscurificator().getImpl());
//        properties.setProperty("kaptcha.word.impl",codeProperties.getWord().getImpl());
//        properties.setProperty("kaptcha.background.impl",codeProperties.getBackground().getImpl());
//        properties.setProperty("kaptcha.background.clear.from",codeProperties.getBackground().getClearFrom());
//        properties.setProperty("kaptcha.background.clear.to",codeProperties.getBackground().getClearto());
//        properties.setProperty("kaptcha.image.width",codeProperties.getImage().getWidth());
//        properties.setProperty("kaptcha.image.height",codeProperties.getImage().getHeight());
//        Config config = new Config(properties);
//        defaultKaptcha.setConfig(config);
//        return defaultKaptcha;
//    }
//}
