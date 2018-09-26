package com.fast.family.utils;

public class HtmlUtils {

    public static void main(String[] args) {

        String specialStr = "<div>111</div>";

        //转换为HTML转义字符表示
        String str1 = org.springframework.web.util.HtmlUtils.htmlEscape(specialStr);

        System.out.println(str1);

        //转换为数据转义表示
        String str2 = org.springframework.web.util.HtmlUtils.htmlEscapeDecimal(specialStr);

        System.out.println(str2);

        //转换为十六进制数据转义表示
        String str3 = org.springframework.web.util.HtmlUtils.htmlEscapeHex(specialStr);

        System.out.println(str3);


        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape(str1));
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape(str2));
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape(str3));

    }
}
