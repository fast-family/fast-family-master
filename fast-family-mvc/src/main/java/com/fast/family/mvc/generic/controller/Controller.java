package com.fast.family.mvc.generic.controller;

import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.mvc.generic.service.GenericService;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:41
 */
public interface Controller<T extends GenericEntity,PK extends Serializable> {

    GenericService<T,PK> getService();
}
