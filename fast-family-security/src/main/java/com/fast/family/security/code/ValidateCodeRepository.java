package com.fast.family.security.code;

/**
 * @author 张顺
 * @version 1.0
 */
public interface ValidateCodeRepository {

    void save(String key, ValidateCode validateCode);

    ValidateCode get(String key);

    void remove(String key);
}
