package com.fast.family.utils;




public class ByteUtils {

    public static int byteToInt(byte b){
        return b & 0xFF;
    }

    public static int byteArrayToInt(byte[] byteArray){
        return   byteArray[3] & 0xFF |
                (byteArray[2] & 0xFF) << 8 |
                (byteArray[1] & 0xFF) << 16 |
                (byteArray[0] & 0xFF) << 24;
    }

    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    /*
	 * 十六进制转二进制数组
	 */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            result[i] = (byte) Integer.parseInt((hex.substring(j,j+2)), 16);
            j+=2;
        }
        return result;
    }

}
