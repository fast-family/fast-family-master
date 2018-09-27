package com.fast.family.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:53
 */
public interface SimpleCrudService<T extends GenericEntity,PK extends Serializable>
        extends InsertService<T,PK>,
                 UpdateService<T,PK>,
                 DeleteService<T,PK>,
                 SelectService<T,PK>{
}
