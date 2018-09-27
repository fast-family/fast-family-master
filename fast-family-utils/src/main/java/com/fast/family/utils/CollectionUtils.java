package com.fast.family.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CollectionUtils {


    /**
     * 判断集合为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    /**
     * 不为空集合
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    public static <T>List<T> emptyList(){
        return Collections.emptyList();
    }


}
