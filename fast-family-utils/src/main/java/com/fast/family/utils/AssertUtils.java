package com.fast.family.utils;

import org.springframework.util.Assert;


public class AssertUtils {


    public static void isNotNull(Object object, String message) {
        Assert.notNull(object,message);
    }

    public static void isNull(Object o,String message){
         Assert.isNull(o,message);
    }

    public static void isTrue(boolean expression,String message){
        Assert.isTrue(expression,message);
    }

}
