package com.fast.family.mvc.generic.controller;

import com.fast.family.commons.json.Response;
import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
public interface DeleteController<T extends GenericEntity, PK extends Serializable>
        extends Controller<T, PK> {


    @ApiOperation("删除(主键)")
    @GetMapping("/delete/{id}")
    default Response deleteById(@PathVariable PK id) {
        this.getService().removeById(id);
        return Response.ok();
    }



    @ApiOperation("删除(条件)")
    @GetMapping("/delete/batch")
    default Response deleteBatch(List<PK> t) {
        this.getService().removeByIds(t);
        return Response.ok();
    }
}
