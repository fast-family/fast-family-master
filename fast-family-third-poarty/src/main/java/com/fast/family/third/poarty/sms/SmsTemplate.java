package com.fast.family.third.poarty.sms;

/**
 * @author 张顺
 * @version 1.0
 */
public interface SmsTemplate<T extends SmsEntity> {

    void sendSms(T t);

}
