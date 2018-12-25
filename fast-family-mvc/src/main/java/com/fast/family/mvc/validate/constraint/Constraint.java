package com.fast.family.mvc.validate.constraint;

import java.lang.reflect.Field;

/**
 * @author 张顺
 * @version 1.0
 */
public interface Constraint {

    void restrict(Field field,Object t);
}
