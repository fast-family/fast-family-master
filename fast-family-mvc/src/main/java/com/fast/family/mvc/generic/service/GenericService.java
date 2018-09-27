package com.fast.family.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:50
 */
public interface GenericService<T extends GenericEntity,PK extends Serializable>
        extends SimpleCrudService<T,PK>{
}
