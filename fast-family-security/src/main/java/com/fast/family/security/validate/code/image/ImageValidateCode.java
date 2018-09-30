package com.fast.family.security.validate.code.image;

import com.fast.family.security.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/27-22:36
 */
@Data
public class ImageValidateCode extends ValidateCode {

    private BufferedImage bufferedImage;


    public ImageValidateCode(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
