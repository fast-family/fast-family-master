package ${packageName}.mapper;

import com.fast.family.mvc.generic.mapper.GenericMapper;
import ${packageName}.entity.${className};
import ${packageName}.dto.${className}DTO;

<#if (type) > 1>
import java.util.List;
</#if>

/**
* <p>
* 描述: ${classComment}Mapper接口
* <p>
*
* @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${className}Mapper extends GenericMapper<${className},Long> {

    ${className}DTO custom();

}
