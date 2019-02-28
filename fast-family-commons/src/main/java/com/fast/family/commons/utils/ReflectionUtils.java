package com.fast.family.commons.utils;

import java.lang.reflect.Method;

/**
 * @author 张顺
 * @version 1.0
 */
public class ReflectionUtils {

    public static void makeAccessible(Method method) {
        org.springframework.util.ReflectionUtils.makeAccessible(method);
    }

    public static <T> T newInstance(final Class<T> clazz) {
        T result;
        try {
            result = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
}
