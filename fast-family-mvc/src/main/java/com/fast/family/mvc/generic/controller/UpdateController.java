package com.fast.family.core.mvc.generic.controller;

import com.fast.family.commons.json.Response;
import com.fast.family.core.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
public interface UpdateController<T extends GenericEntity, PK extends Serializable>
        extends Controller<T, PK> {

    @ApiOperation("更新")
    @PostMapping("/update")
    default Response updateById(@RequestBody T t) {
        this.getService().updateById(t);
        return Response.ok();
    }

    @ApiOperation("批量更新")
    @PostMapping("/update/batch")
    default Response updateBatch(@RequestBody List<T> list) {
        this.getService().updateBatchById(list);
        return Response.ok();
    }
}
