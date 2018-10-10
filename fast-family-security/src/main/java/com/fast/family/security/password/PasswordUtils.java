package com.fast.family.security.password;

import com.fast.family.commons.exception.NoAuthException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/10-17:32
 */
public class PasswordUtils {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();


    public static boolean matchPassword(String originalPassword,String encryptPassword){
        return matchPassword(originalPassword,encryptPassword,8,1);
    }


    public static boolean matchPassword(String originalPassword,String encryptPassword,int saltLength){
        return matchPassword(originalPassword,encryptPassword,saltLength,1);
    }


    /**
     * 验证密码是否匹配
     * @param originalPassword 原密码
     * @param encryptPassword 加密后密码
     * @param saltLength 盐长度
     * @return
     */
    public static boolean matchPassword(String originalPassword,String encryptPassword,
                                        int saltLength,int hashIterations){
        try {
            byte[] salt = Hex.decode(encryptPassword.substring(0,saltLength * 4));
            String md5HexStr = md5Hex(originalPassword.getBytes(),salt,hashIterations);
            return md5HexStr.equals(encryptPassword.substring(saltLength * 4));
        } catch (Exception e) {
            throw new NoAuthException("验证密码失败",e);
        }
    }

    public static String encryptPassword(byte[] data){
        return encryptPassword(data,8);
    }

    public static String encryptPassword(byte[] data,int saltLength){
        return encryptPassword(data,saltLength,1);
    }


    public static String encryptPassword(byte[] data,int saltLength,int hashIterations){
        byte[] saltBytes = new byte[saltLength];
        SECURE_RANDOM.nextBytes(saltBytes);
        return String.valueOf(Hex.encode(saltBytes)) + md5Hex(data,saltBytes,hashIterations);
    }


    /**
     * 转md5 16进制数据
     * @param data 元数据
     * @param salt 盐
     * @param hashIterations hash次数
     * @return
     */
    public static String md5Hex(byte[] data,byte[] salt,int hashIterations){
        MessageDigest digest = DigestUtils.getMd5Digest();
        if (salt != null){
            digest.reset();
            digest.update(salt);
        }
        byte[] digestData = digest.digest(data);
        for (int i = 0;i < hashIterations;i++){
            digest.reset();
            digestData = digest.digest(digestData);
        }
        return String.valueOf(Hex.encode(digestData));
    }
}
