package com.fast.family.mvc.generic.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public abstract class GenericEntity<PK extends Serializable> {

    @Id
    @KeySql(genId = GlobalIDGenerator.class)
    @Column(name = "id")
    private PK id;

}
