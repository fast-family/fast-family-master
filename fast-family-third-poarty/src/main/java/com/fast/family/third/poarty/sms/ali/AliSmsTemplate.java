package com.fast.family.third.poarty.sms.ali;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

/**
 * @author 张顺
 * @version 1.0
 */
public interface AliSmsTemplate<T extends AliSmsEntity> {

    default IAcsClient getAcsClient(){ return null;};

    default SendSmsRequest getAliSmsRequest(T t){ return null;};
}
