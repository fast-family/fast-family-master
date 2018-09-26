package com.fast.family.utils;

import java.math.BigDecimal;


public class StringUtils {

    public static boolean isEmpty(String param){
        if (org.springframework.util.StringUtils.isEmpty(param)){
            return true;
        }
        return false;
    }

    public static boolean equals(CharSequence cs1,CharSequence cs2){
        return org.apache.commons.lang3.StringUtils.equals(cs1,cs2);
    }

    public static boolean notEquals(CharSequence cs1,CharSequence cs2){
        return !equals(cs1,cs2);
    }

    public static boolean equalsIgnoreCase(CharSequence cs1,CharSequence cs2){
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(cs1,cs2);
    }


    public static boolean isBlank(CharSequence cs1){
        return org.apache.commons.lang3.StringUtils.isBlank(cs1);
    }

    public static boolean isNotBlank(CharSequence cs1){
        return !isBlank(cs1);
    }

    /**
     * 首字母转换小写
     * @param attrName
     * @return
     */
    public static String uncapitalize(String attrName){
        return org.apache.commons.lang3.StringUtils.uncapitalize(attrName);
    }

    /**
     * 判断字符串是否为数字,健壮性强于正则
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    public static boolean hasText(String str){
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static boolean hasLength(String str){
        return org.springframework.util.StringUtils.hasLength(str);
    }

    public static String trim(String str){
        return org.apache.commons.lang3.StringUtils.trim(str);
    }

    /**
     * 从左边开始截取字符串
     * @param str 字符串
     * @param len 截取结束位置
     * @return
     */
    public static String left(String str,int len){
        return org.apache.commons.lang3.StringUtils.left(str,len);
    }

    /**
     * 从右边开始转换
     * 如果str和len相等则不转换
     *
     * 例如:   String name = "张霸王";
     *         String name2 = right(name,1);
     *         rightPad(name2,length(name),"*")
     *         最后打印结果为**王
     *
     * @param str 无需转换字符串
     * @param len 字符串总长度
     * @param padStr 替换字符串类型
     * @return
     * @return
     */
    public static String leftPad(String str,int len,String padStr){
        return org.apache.commons.lang3.StringUtils.leftPad(str,len,padStr);
    }


    /**
     * 从右边截取字符串
     * @param str 字符串
     * @param len 截取结束位置
     * @return
     */
    public static String right(String str,int len){
        return org.apache.commons.lang3.StringUtils.right(str,len);
    }

    /**
     * 从右边开始转换
     * 如果str和len相等则不转换
     *
     * 例如:   String name = "张霸王";
     *         String name2 = left(name,1);
     *         rightPad(name2,length(name),"*")
     *         最后打印结果为张**
     *
     * @param str 无需转换字符串
     * @param len 字符串总长度
     * @param padStr 替换字符串类型
     * @return
     */
    public static String rightPad(String str,int len,String padStr){
        return org.apache.commons.lang3.StringUtils.rightPad(str,len,padStr);
    }

    public static int length(CharSequence charSequence){
        return org.apache.commons.lang3.StringUtils.length(charSequence);
    }


    /**
     * 删除字符串
     * @param str 字符串
     * @param removeStr 被删除字符串(且只能从开始字符串删除不能从中截取删除)
     * @return
     */
    public static String removeStart(String str,String removeStr){
        return org.apache.commons.lang3.StringUtils.removeStart(str,removeStr);
    }


    public static int indexOf(CharSequence str,CharSequence str1){
        return org.apache.commons.lang3.StringUtils.indexOf(str,str1);
    }

    public static String mid(String str, int pos, int len){
        return org.apache.commons.lang3.StringUtils.mid(str,pos,len);
    }

}
