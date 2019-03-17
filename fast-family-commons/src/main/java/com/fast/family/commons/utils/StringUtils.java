package com.fast.family.commons.utils;

/**
 * @author 张顺
 * @version 1.0
 */
public class StringUtils {


    public static boolean isEmpty(String str){
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean equals(String str,String str1){
        return org.apache.commons.lang3.StringUtils.equals(str,str1);
    }

    public static boolean equalsIgnoreCase(String str,String str1){
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str,str1);
    }
}
