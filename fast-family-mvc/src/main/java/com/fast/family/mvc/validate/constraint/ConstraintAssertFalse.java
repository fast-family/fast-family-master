package com.fast.family.mvc.validate.constraint;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class ConstraintAssertFalse implements Constraint{


    @Override
    public void restrict(Field field, Object t) {

    }
}
