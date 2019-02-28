package com.fast.family.mvc.generic.controller;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface SimpleCrudController<T extends GenericEntity, PK extends Serializable>
        extends InsertController<T, PK>, DeleteController<T, PK>, SelectController<T, PK>, UpdateController<T, PK> {
}
