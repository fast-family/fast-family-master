package ${packageName}.controller;

import com.fast.family.mvc.generic.controller.GenericController;
import com.fast.family.mvc.generic.service.GenericService;
import ${packageName}.entity.${className};
import ${packageName}.service.${className}Service;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* 描述: ${classComment}控制器
* <p>
    *
    * @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
    */
    @RestController
    @RequestMapping("${url}")
    @Slf4j
    @Api(tags = "${classComment}接口")
    public class ${className}Controller extends GenericController<${className},Long>{

    @Autowired
    private ${className}Service ${className?uncap_first}Service;


    @Override
    public GenericService<${className}, Long> getService() {
    return this.${className?uncap_first}Service;
    }
    }