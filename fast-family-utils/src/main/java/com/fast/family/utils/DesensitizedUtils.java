package com.fast.family.utils;

public class DesensitizedUtils {


    /**
     * 只显示第一个汉字其他以*替代
     * @param fullName
     * @return
     */
    public static String chineseName(String fullName){
        String name = StringUtils.left(fullName,1);
        return StringUtils.rightPad(name,StringUtils.length(fullName),"*");
    }


    /**
     * 身份证号显示后四位其他隐藏
     * @param idCard
     * @return
     */
    public static String idCardNum(String idCard){
        String num = StringUtils.right(idCard,4);
        return StringUtils.leftPad(num,StringUtils.length(idCard),"*");
    }

    /**
     * 固定点换显示后4位其他显示*
     * @param num
     * @return
     */
    public static String fixedPhone(String num){
        return StringUtils.leftPad(StringUtils.right(num,4),StringUtils.length(num),"*");
    }


    /**
     * 手机号码前三位,后四位,其他隐藏
     * @return
     */
    public static String mobilePhone(String num){
        return StringUtils.left(num,3).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(num,4),StringUtils.length(num),"*"),"***"));
    }


    public static String address(String address,int sensitiveSize){
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address,length - sensitiveSize),length,"*");
    }

    public static String email(String email){
        int index = StringUtils.indexOf(email,"@");
        if(index <= 1){
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email,1),index,"*")
                    .concat(StringUtils.mid(email,index,StringUtils.length(email)));
        }
    }

    /**
     * 【银行卡号】前六位，后四位，其他用星号隐藏每位1个星号，比如：6222600**********1234
     *
     * @param cardNum
     * @return
     */
    public static String bankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * 【密码】密码的全部字符都用*代替，比如：******
     *
     * @param password
     * @return
     */
    public static String password(String password) {
        if (StringUtils.isBlank(password)) {
            return "";
        }
        String pwd = StringUtils.left(password, 0);
        return StringUtils.rightPad(pwd, StringUtils.length(password), "*");
    }

}
