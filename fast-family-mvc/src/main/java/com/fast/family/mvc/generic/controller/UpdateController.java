package com.fast.family.mvc.generic.controller;

import com.fast.family.mvc.generic.entity.GenericEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
public interface UpdateController<T extends GenericEntity,PK extends Serializable>
        extends Controller<T,PK>{

    @ApiOperation("更新")
    @PostMapping("/update")
    default ResponseEntity updateById(@RequestBody T t){
        this.getService().updateById(t);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("批量更新")
    @PostMapping("/update/batch")
    default ResponseEntity updateBatch(@RequestBody List<T> list){
        this.getService().updateBatch(list);
        return ResponseEntity.ok().build();
    }
}
