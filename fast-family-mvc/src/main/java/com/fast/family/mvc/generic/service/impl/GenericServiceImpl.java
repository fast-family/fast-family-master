package com.fast.family.mvc.generic.service.impl;

import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.mvc.generic.service.GenericService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:53
 */
@Service
public abstract class GenericServiceImpl<T extends GenericEntity,PK extends Serializable>
        implements GenericService<T,PK>{
}
