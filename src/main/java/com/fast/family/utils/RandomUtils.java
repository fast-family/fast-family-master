package com.fast.family.utils;

import java.util.Random;

public class RandomUtils {

    private static final String SOURCES = "0123456789ABCDEFGHYJKMLIQPOI";

    public static String genRandom(int bit,int bound){
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < bit; j++)
        {
            flag.append(SOURCES.charAt(rand.nextInt(bound)) + "");
        }
        return flag.toString();
    }

}
