package com.fast.family.commons.validate.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateCode implements Serializable{

    private String code;

    private long expireTime;
}
