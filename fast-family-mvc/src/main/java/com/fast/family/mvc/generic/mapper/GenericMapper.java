package com.fast.family.mvc.generic.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface GenericMapper<T extends GenericEntity, PK extends Serializable> extends
        BaseMapper<T> {
}
