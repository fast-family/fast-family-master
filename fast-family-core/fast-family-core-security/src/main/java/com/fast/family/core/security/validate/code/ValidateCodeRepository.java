package com.fast.family.core.security.validate.code;

/**
 * @author 张顺
 * @version 1.0
 */
public interface ValidateCodeRepository {

    void save(String key, ValidateCode validateCode);

    ValidateCode get(String key);

    void remove(String key);
}
