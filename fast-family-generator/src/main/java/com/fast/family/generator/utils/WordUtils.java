package com.fast.family.generator.utils;


public class WordUtils {


    /**
     * 列名转换java属性名
     * @param columnName
     * @return
     */
    public static String columnToJava(String columnName){
        return org.apache.commons.lang3.text.WordUtils.capitalizeFully(columnName,
                new char[]{'_'}).replace("_","");
    }

}
