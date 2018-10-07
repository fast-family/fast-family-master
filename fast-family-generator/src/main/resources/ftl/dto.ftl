package ${packageName}.dto;


import ${packageName}.entity.${className};
import com.fast.family.mvc.generic.service.domain.AbstractPersistableBusinessObjectAdapter;

;

/**
* <p>
* 描述: ${classComment}Dto实体
* <p>
*
* @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
*/
public class ${className}DTO extends AbstractPersistableBusinessObjectAdapter<${className}>{


    public ${className}DTO(Class<${className}> persistableEntityClass) {
        super(persistableEntityClass);
    }

    @Override
    protected void customizeConvert(${className} ${className?uncap_first}) {

    }

    @Override
    protected void customizeFill(${className} ${className?uncap_first}) {

    }
}
