package com.fast.family.mvc.validate;

import java.lang.annotation.Annotation;

/**
 * @author 张顺
 * @version 1.0
 */
public interface Validator<T extends Annotation,K> {


    ValidateResponse validate(Annotation[] annotations);

}
