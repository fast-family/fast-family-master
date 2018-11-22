package com.fast.family.mvc.generic.entity;

import tk.mybatis.mapper.genid.GenId;

import java.io.Serializable;


public interface IDGenerator<PK extends Serializable> extends GenId<PK>{



}
