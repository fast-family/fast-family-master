package com.fast.family.mvc.generic.controller;

import com.fast.family.commons.json.Response;
import com.fast.family.commons.page.Pageble;
import com.fast.family.mvc.generic.entity.GenericEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
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
    default Response<PageInfo<T>> search(T t, Pageble pageble) {
        return Response.ok(this.getService().search(t, pageble));
    }

    @ApiOperation("查询(主键)")
    @GetMapping("/select")
    default Response<T> selectById(@RequestParam PK id) {
        return Response.ok(this.getService().selectById(id).get());
    }

    @ApiOperation("查询单个对象")
    @GetMapping("/select/one")
    default Response<T> selectOne(T t) {
        return Response.ok(this.getService().selectOne(t).get());
    }

    @ApiOperation("查询列表")
    @GetMapping("/select/list")
    default Response<List<T>> selectList(T t) {
        return Response.ok(this.getService().selectList(t).findAny().get());
    }

    @ApiOperation("查询全部")
    @GetMapping("/select/all")
    default Response<List<T>> selectAll() {
        return Response.ok(this.getService().selectAll().findAny().get());
    }

    @ApiOperation("查询(条件)")
    @GetMapping("/select/example")
    default Response<List<T>> selectByExample(T t) {
        return Response.ok(this.getService().selectByExample(t).findAny().get());
    }

    @ApiOperation("检测是否存在(主键)")
    @GetMapping("/exists")
    default Response existsWithPrimaryKey(@RequestParam PK id) {
        return Response.ok(this.getService().existsWithPrimaryKey(id));
    }

}
