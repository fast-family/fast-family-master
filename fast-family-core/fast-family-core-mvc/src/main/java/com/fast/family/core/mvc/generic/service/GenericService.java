package com.fast.family.core.mvc.generic.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fast.family.core.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface GenericService<T extends GenericEntity, PK extends Serializable>
        extends IService<T> {
}
