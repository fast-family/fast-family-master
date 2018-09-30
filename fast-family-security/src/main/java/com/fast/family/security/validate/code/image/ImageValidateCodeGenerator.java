package com.fast.family.security.validate.code.image;

import com.fast.family.security.validate.code.ValidateCodeGenerator;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/27-22:36
 */
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Override
    public ImageValidateCode generate() {
        String createText = defaultKaptcha.createText();
        BufferedImage bufferedImage = defaultKaptcha.createImage(createText);
        return new ImageValidateCode(bufferedImage);
    }
}
