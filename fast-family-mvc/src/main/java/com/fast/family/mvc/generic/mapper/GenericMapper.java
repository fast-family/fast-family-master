package com.fast.family.mvc.generic.mapper;

import com.fast.family.mvc.generic.entity.GenericEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface GenericMapper<T extends GenericEntity,PK extends Serializable> extends
        Mapper<T>,MySqlMapper<T> {
}
