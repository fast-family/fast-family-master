package com.fast.family.commons.validate.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:26
 */
@Slf4j
public class ImMemoryValidateCodeRepository implements ValidateCodeRepository{

    private static Map<String,ValidateCode> codeMap = new ConcurrentHashMap<>();

    @Override
    public void save(String key,ValidateCode validateCode, ValidateCodeType validateCodeType) {
        codeMap.put(key,validateCode);
    }

    @Override
    public ValidateCode get(String key, ValidateCodeType validateCodeType) {
        return codeMap.get(key);
    }

    @Override
    public void remove(String key, ValidateCodeType validateCodeType) {
        Iterator<Map.Entry<String,ValidateCode>> codeIterator = codeMap.entrySet().iterator();
        while (codeIterator.hasNext()){
            Map.Entry<String,ValidateCode> codeEntry = codeIterator.next();
            if (codeEntry.getKey().equals(key)){
                codeIterator.remove();
            }
        }

    }
}
