package com.fast.family.commons.utils;

/**
 * @author 张顺
 * @version 1.0
 */
public class BooleanUtils extends org.apache.commons.lang3.BooleanUtils {

    private static final String TRUE_CHINESE = "是";

    private static final String FALSE_CHINESE = "否";

    public static boolean converterBoolean(String args) {
        if (StringUtils.isEmpty(args)) {
            return false;
        }
        final String trimValue = args.trim();
        return StringUtils.equalsIgnoreCase("yes", trimValue)
                || StringUtils.equals("1", trimValue)
                || StringUtils.equals(TRUE_CHINESE, trimValue)
                || StringUtils.equalsIgnoreCase("true", trimValue)
                || StringUtils.equalsIgnoreCase("y", trimValue)
                || StringUtils.equalsIgnoreCase("on", trimValue);
    }


    public static String converterChinese(boolean bool) {
        return bool ? TRUE_CHINESE : FALSE_CHINESE;
    }

}
