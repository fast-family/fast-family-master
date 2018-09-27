package com.fast.family.commons.validate.code;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:23
 */
public interface ValidateCodeRepository {

    void save(String key, ValidateCode validateCode, ValidateCodeType validateCodeType);

    ValidateCode get(String key, ValidateCodeType validateCodeType);

    void remove(String key, ValidateCodeType validateCodeType);
}
