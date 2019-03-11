package com.fast.family.core.mvc.generic.controller;



import com.fast.family.core.mvc.generic.entity.GenericEntity;
import com.fast.family.core.mvc.generic.service.GenericService;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface Controller<T extends GenericEntity, PK extends Serializable> {

    GenericService<T, PK> getService();
}
