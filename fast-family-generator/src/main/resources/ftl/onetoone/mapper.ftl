package ${packageName}.mapper;

import com.fast.family.mvc.generic.mapper.GenericMapper;
import ${packageName}.entity.${className};
import ${packageName}.dto.${className}DTO;


import java.util.List;


/**
* <p>
    * 描述: ${classComment}Mapper接口
    * <p>
    *
    * @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
    */
    public interface ${className}Mapper extends GenericMapper<${className},Long> {

${className}DTO custom(${className}DTO ${className?uncap_first}DTO);

    }
