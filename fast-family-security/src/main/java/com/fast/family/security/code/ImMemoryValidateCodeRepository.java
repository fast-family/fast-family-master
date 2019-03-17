package com.fast.family.security.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class ImMemoryValidateCodeRepository implements ValidateCodeRepository {

    private static Map<String, ValidateCode> codeMap = new ConcurrentHashMap<>();

    @Override
    public void save(String key, ValidateCode validateCode) {
        codeMap.put(key, validateCode);
    }

    @Override
    public ValidateCode get(String key) {
        return codeMap.get(key);
    }

    @Override
    public void remove(String key) {
        Iterator<Map.Entry<String, ValidateCode>> codeIterator = codeMap.entrySet().iterator();
        while (codeIterator.hasNext()) {
            Map.Entry<String, ValidateCode> codeEntry = codeIterator.next();
            if (codeEntry.getKey().equals(key)) {
                codeIterator.remove();
            }
        }

    }
}
