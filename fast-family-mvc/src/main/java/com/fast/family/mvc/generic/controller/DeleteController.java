package com.fast.family.mvc.generic.controller;

import com.fast.family.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
public interface DeleteController<T extends GenericEntity,PK extends Serializable>
        extends Controller<T,PK>{


    @ApiOperation("删除(主键)")
    @GetMapping("/delete/{id}")
    default void deleteById(@PathVariable PK id){
        this.getService().selectById(id);
    }

    @ApiOperation("删除(对象)")
    @GetMapping("/delete")
    default void delete(T t){
        this.getService().delete(t);
    }

    @ApiOperation("删除(条件)")
    @GetMapping("/delete/example")
    default void deleteByExample(T t){
        this.getService().deleteByExample(t);
    }
}
