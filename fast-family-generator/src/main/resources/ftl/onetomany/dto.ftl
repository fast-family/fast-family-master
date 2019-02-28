package ${packageName}.dto;


import ${packageName}.entity.${className};
import com.fast.family.mvc.generic.service.domain.AbstractPersistableBusinessObjectAdapter;

import lombok.Data;

/**
* <p>
    * 描述: ${classComment}Dto实体
    * <p>
    *
    * @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Data
    public class ${className}DTO extends AbstractPersistableBusinessObjectAdapter<${className}>{


    <#list masterTableInfo.columnInfoList as column>
        <#if column.columnComment?length gt 0>
    /**
    * ${column.columnComment}
    */
        </#if>
    private ${column.dataType} ${column.columnJavaName};
    </#list>

    <#list slaveTableInfo.columnInfoList as column>
        <#if column.columnComment?length gt 0>
    /**
    * ${column.columnComment}
    */
        </#if>
    private ${column.dataType} ${column.columnJavaName};
    </#list>

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
