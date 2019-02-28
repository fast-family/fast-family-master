package com.fast.family.commons.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author 张顺
 * @version 1.0
 */
public class GsonUtils {

    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
            .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
            .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") //时间转化为特定格式
            .setPrettyPrinting() //对结果进行格式化，增加换行
            .disableHtmlEscaping()//防止特殊字符出现乱码
            .create();


    public static String toJson(Object o, Type type) {
        return gson.toJson(o, type);
    }

    public static <T> T fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }
}
