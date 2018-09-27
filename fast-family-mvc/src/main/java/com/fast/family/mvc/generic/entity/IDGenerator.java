package com.fast.family.mvc.generic.entity;

import tk.mybatis.mapper.genid.GenId;

import java.io.Serializable;


public interface IDGenerator<PK extends Serializable> extends GenId<PK>{

    PK generator();

    IDGenerator<Long> SNOW_FLAKE = SnowflakeIdGenerator.getInstance()::nextId;

    IDGenerator<String> UUID = () -> java.util.UUID.randomUUID().toString().replace("-","");

    @Override
    default PK genId(String table, String column) {
        return (PK) SNOW_FLAKE.generator();
    }

}
