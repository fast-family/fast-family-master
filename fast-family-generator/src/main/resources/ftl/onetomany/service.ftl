package ${packageName}.service;

import com.fast.family.mvc.generic.service.GenericService;
import ${packageName}.entity.${className};

import java.util.List;


/**
* <p>
* 描述: ${classComment}业务接口
* <p>
*
* @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${className}Service extends GenericService<${className},Long>{

    List<${className}DTO> custom(${className}DTO ${className?uncap_first}DTO);
}