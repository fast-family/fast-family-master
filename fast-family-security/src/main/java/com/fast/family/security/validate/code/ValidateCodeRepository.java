package com.fast.family.security.validate.code;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:23
 */
public interface ValidateCodeRepository {

    void save(String key, ValidateCode validateCode);

    ValidateCode get(String key);

    void remove(String key);
}
