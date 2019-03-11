package com.fast.family.core.mvc.generic.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fast.family.commons.json.Response;
import com.fast.family.core.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
public interface SelectController<T extends GenericEntity, PK extends Serializable>
        extends Controller<T, PK> {

    @ApiOperation("分页查询")
    @GetMapping("/search")
    default Response<IPage<T>> search(T t, IPage pageble) {
        return Response.ok(this.getService().pageMaps(pageble, new QueryWrapper<>()));
    }

    @ApiOperation("查询(主键)")
    @GetMapping("/select")
    default Response<T> selectById(@RequestParam PK id) {
        return Response.ok(this.getService().getById(id));
    }

    @ApiOperation("查询单个对象")
    @GetMapping("/select/one")
    default Response<T> selectOne(T t) {
        return Response.ok(this.getService().getOne(new QueryWrapper<>()));
    }

    @ApiOperation("查询列表")
    @GetMapping("/select/list")
    default Response<List<T>> selectList(T t) {
        return Response.ok(this.getService().list(new QueryWrapper<>()));
    }

    @ApiOperation("查询全部")
    @GetMapping("/select/all")
    default Response<List<T>> selectAll() {
        return Response.ok(this.getService().list());
    }




}
