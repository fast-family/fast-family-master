package com.fast.family.mvc.generic.entity;

import com.fast.family.commons.utils.SpringContextUtils;


/**
 * @author 张顺
 * @version 1.0
 */
public class GlobalIDGenerator<Long> implements IDGenerator<java.lang.Long>{


    private final SnowflakeIdGenerator snowflakeIdGenerator =
            SpringContextUtils.getBean(SnowflakeIdGenerator.class);

    @Override
    public java.lang.Long genId(String s, String s1) {
        return snowflakeIdGenerator.nextId();
    }
}
