package com.fast.family.utils;

;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;


public class DigestUtils {



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
