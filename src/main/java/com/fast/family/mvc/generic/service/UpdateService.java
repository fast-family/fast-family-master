package com.fast.family.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:51
 */
public interface UpdateService<T extends GenericEntity,PK extends Serializable>
        extends Service<T,PK>{

    default void updateById(T t){
        this.getMapper().updateByPrimaryKey(t);
    }

    default void updateBatch(List<T> list){
        for (T t : list){
            this.getMapper().updateByPrimaryKey(t);
        }
    }
}
