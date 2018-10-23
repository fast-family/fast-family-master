package com.fast.family.mvc.generic.controller;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public abstract class GenericController<T extends GenericEntity,PK extends Serializable>
            implements SimpleCrudController<T,PK>{
}
