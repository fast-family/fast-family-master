package com.fast.family.utils;

import java.util.Arrays;
import java.util.List;


public class ArrayUtils {

    public static <T>List<T> asList(T... a){
        return Arrays.asList(a);
    }
}
