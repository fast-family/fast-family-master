package com.fast.family.mvc.generic.entity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/22-12:16
 */
public class GlobalIDGenerator<PK extends Serializable> implements IDGenerator<PK>{


    @Override
    public PK generator() {
        return null;
    }
}
