package ${packageName}.service.impl;


import com.fast.family.mvc.generic.mapper.GenericMapper;
import com.fast.family.mvc.generic.service.impl.GenericServiceImpl;
import ${packageName}.entity.${className};
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.service.${className}Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* <p>
    * 描述: ${classComment}业务实现类
    * <p>
    *
    * @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Service
    @Slf4j
    public class ${className}ServiceImpl extends GenericServiceImpl<${className},Long> implements ${className}Service{

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;


    @Override
    public GenericMapper<${className}, Long> getMapper() {
    return ${className?uncap_first}Mapper;
    }
    }