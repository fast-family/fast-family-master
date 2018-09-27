package com.fast.family.commons.validate.code.image;

import com.fast.family.commons.validate.code.ValidateCodeProperties;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/27-23:12
 */
@Data
@ConfigurationProperties(prefix = "fast.family.validate.code.image")
public class ImageCodeProperties extends ValidateCodeProperties{


    @Getter
    private final Border border = new Border();

    private final Producer producer = new Producer();

    private final Noise noise = new Noise();

    private final TextProducer textProducer = new TextProducer();

    private final Obscurificator obscurificator = new Obscurificator();

    private final Image image = new Image();

    private final Background background = new Background();

    private final Word word = new Word();

    @Data
    public static class Image{

        private String width;

        private String height;
    }

    @Data
    public static class Background{

        private String impl;

        private String clearFrom;

        private String clearto;

    }

    @Data
    public static class Word{

        private String impl;

    }

    @Data
    public static class Obscurificator{

        private String impl;

    }

    @Data
    public static class Border{

        private String border;

        private String color;

        private String thickness;
    }

    @Data
    public static class Producer{

        private String impl;

    }

    @Data
    public static class Noise{

        private String impl;

        private String color;

    }

    @Data
    public static class TextProducer{

        private String impl;

        private String charStr;

        private String charLength;

        private String fontNames;

        private String fontSize;

        private String fontColor;

        private String charSpace;
    }

}
