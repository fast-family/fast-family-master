package com.fast.family.mvc.generic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public abstract class GenericEntity<PK extends Serializable> {


    private PK id;

}
