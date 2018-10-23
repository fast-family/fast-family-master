package com.fast.family.mvc.generic.entity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public class GlobalIDGenerator<PK extends Serializable> implements IDGenerator<PK>{


    @Override
    public PK generator() {
        return null;
    }
}
