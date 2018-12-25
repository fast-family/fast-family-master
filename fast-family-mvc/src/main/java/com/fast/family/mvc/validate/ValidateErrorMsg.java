package com.fast.family.mvc.validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张顺
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidateErrorMsg {

    private String name;//属性名

    private String errorMsg;//错误信息
}
