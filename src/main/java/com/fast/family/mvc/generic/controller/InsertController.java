package com.fast.family.mvc.generic.controller;

import com.fast.family.commons.json.Response;
import com.fast.family.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:45
 */
public interface InsertController<T extends GenericEntity,PK extends Serializable>
        extends Controller<T,PK>{


    @ApiOperation("新增")
    @PostMapping("/insert")
    default Response insert(T t){
        this.getService().insert(t);
        return Response.ok();
    }

    @ApiOperation("批量新增")
    @PostMapping("/insert/batch")
    default Response<T> insertBatch(List<T> list){
        this.getService().insertBatch(list);
        return Response.ok();
    }
}
