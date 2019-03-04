package com.fast.family.core.mvc.generic.service;

import com.fast.family.commons.page.Pageble;
import com.fast.family.mvc.generic.entity.GenericEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 张顺
 * @version 1.0
 */
public interface SelectService<T extends GenericEntity, PK extends Serializable>
        extends Service<T, PK> {

    default PageInfo<T> search(T t, Pageble pageble) {
        PageHelper.startPage(pageble.getPage(), pageble.getSize());
        List<T> list = this.getMapper().selectByExample(t);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    default Optional<T> selectById(PK id) {
        return Optional.ofNullable(this.getMapper().selectByPrimaryKey(id));
    }

    default Optional<T> selectOne(T t) {
        return Optional.ofNullable(this.getMapper().selectOne(t));
    }

    default Stream<List<T>> selectList(T t) {
        return Stream.of(this.getMapper().select(t));
    }

    default Stream<List<T>> selectAll() {
        return Stream.of(this.getMapper().selectAll());
    }

    default Stream<List<T>> selectByExample(T t) {
        return Stream.of(this.getMapper().selectByExample(t));
    }

    default boolean existsWithPrimaryKey(PK id) {
        return this.getMapper().existsWithPrimaryKey(id);
    }
}
