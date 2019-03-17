package com.fast.family.mvc.generic.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.mvc.generic.service.GenericService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
@Service
public abstract class GenericServiceImpl<U extends BaseMapper<T>,T extends GenericEntity, PK extends Serializable>
        extends ServiceImpl<U,T> implements GenericService<T, PK> {
}
