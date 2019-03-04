package com.fast.family.core.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface DeleteService<T extends GenericEntity, PK extends Serializable>
        extends Service<T, PK> {

    default void deleteById(PK id) {
        this.getMapper().deleteByPrimaryKey(id);
    }

    default void delete(T t) {
        this.getMapper().delete(t);
    }

    default void deleteByExample(T t) {
        this.getMapper().deleteByExample(t);
    }
}
