package com.fast.family.core.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
public interface InsertService<T extends GenericEntity, PK extends Serializable>
        extends Service<T, PK> {

    default void insert(T t) {
        this.getMapper().insert(t);
    }

    default void insertBatch(List<T> list) {
        this.getMapper().insertList(list);
    }
}
