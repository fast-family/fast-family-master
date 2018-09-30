package ${packageName}.entity;

import com.fast.family.mvc.generic.entity.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
* <p>
* 描述: ${classComment}实体
* <p>
* @created ${sysTime?string("yyyy-MM-dd HH:mm:ss")}
*/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "${tableInfo.tableName}")
@ApiModel(value = "${classComment}实体")
public class ${className} extends GenericEntity<Long> {

<#list tableInfo.columnInfoList as column>
    <#if column.columnComment?length gt 0>
        /**
        * ${column.columnComment}
        */
    </#if>
    <#--<#if column.columnKey == "PRI">-->
        <#--@Id-->
    <#--</#if>-->

    <#if  column.columnKey != "PRI">
    @Column(name = "${column.columnName}")
        <#if column.isNullable == "NO">
    @ApiModelProperty(required = true, dataType = "${column.dataType}", name = "${column.columnComment}")
            <#--<#if column.dataType =="String">-->
    <#--@Size(max = ${column.length},min = 1,message = "${column.columnComment}需在1~${column.length}长度之间")-->
            <#--<#else>-->
            <#--&lt;#&ndash;@Length(max =  ${column.length},min = 1,message = "${column.columnComment}需在1~${column.length}长度之间")&ndash;&gt;-->
            <#--</#if>-->
        <#else>
    @ApiModelProperty(required = false, dataType = "${column.dataType}", name = "${column.columnComment}")
        </#if>
    private ${column.dataType} ${column.columnJavaName};
    </#if>

</#list>




}