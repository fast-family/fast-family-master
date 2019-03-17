package com.fast.family.core.mvc.generic.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public abstract class BaseDTO {

    protected String createdBy;//创建人

    protected String lastModifiedBy;//最后更新人

    protected Date createdTime;//创建时间

    protected Date lastModifiedTime;//最后更新时间
}
