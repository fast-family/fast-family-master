package com.fast.family.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;


public class DigestUtils {

    /**
     * base64编码
     * @param params
     * @return
     */
    public static String base64Encrypt(String params){
        try {
            return new BASE64Encoder().encode(params.getBytes());
        } catch (Exception e){
            throw new RuntimeException("Base64加密异常",e);
        }

    }

    /**
     * base64解码
     * @param encode
     * @return
     */
    public static String base64Decrypt(String encode){
        try {
            return new String(new BASE64Decoder().decodeBuffer(encode));
        } catch (Exception e) {
            throw new RuntimeException("Base64解密异常",e);
        }
    }

//    /**
//     * 根据摘要算法计算文件摘要值
//     * @param alg
//     * @param file
//     * @return
//     */
//    public static String digest(String alg,String file){
//        byte[] digest = digestCommonAlg(alg,file);
//        return new String(digest);
//    }

    private static byte[] digestCommonAlg(String alg,String file){
        FileInputStream fis = null;
        try {
            MessageDigest mDigest = MessageDigest.getInstance(alg);
            fis = new FileInputStream(file);
            byte[] byteArray = new byte[1024];
            int hasRead = 0;
            while ((hasRead = fis.read(byteArray)) > 0){
                mDigest.update(byteArray,0,hasRead);
            }
            byte[] digest = mDigest.digest();
            return digest;
        } catch (Exception e) {
            throw new RuntimeException(alg+"加密异常",e);
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
