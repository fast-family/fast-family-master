package com.fast.family.utils;


import java.util.Base64;

/**
 * Created by hp on 2018/1/8.
 */
public class Base64Utils {

    /**
     * base64加密
     * @param data
     * @return
     */
    public static String base64Encoder(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }


    public static String base64Decode(String data){
        return new String(Base64.getDecoder().decode(data));
    }
}
