package com.fast.family.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.mvc.generic.mapper.GenericMapper;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:50
 */
public interface Service<T extends GenericEntity,PK extends Serializable> {

    GenericMapper<T,PK> getMapper();
}
