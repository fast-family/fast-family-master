package com.fast.family.mvc.generic.service;

import com.fast.family.mvc.generic.entity.GenericEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:52
 */
public interface SelectService<T extends GenericEntity,PK extends Serializable>
        extends Service<T,PK>{


    default Optional<T> selectById(PK id){
        return Optional.ofNullable(this.getMapper().selectByPrimaryKey(id));
    }

    default Optional<T> selectOne(T t){
        return Optional.ofNullable(this.getMapper().selectOne(t));
    }

    default Stream<List<T>> selectList(T t){
        return Stream.of(this.getMapper().select(t));
    }

    default Stream<List<T>> selectAll(){
        return Stream.of(this.getMapper().selectAll());
    }

    default Stream<List<T>> selectByExample(T t){
        return Stream.of(this.getMapper().selectByExample(t));
    }

    default boolean existsWithPrimaryKey(PK id){
        return this.getMapper().existsWithPrimaryKey(id);
    }
}
