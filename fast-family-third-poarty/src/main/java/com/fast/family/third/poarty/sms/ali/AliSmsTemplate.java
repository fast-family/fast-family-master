package com.fast.family.third.poarty.sms.ali;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.IAcsClient;

/**
 * @author 张顺
 * @version 1.0
 */
public interface AliSmsTemplate<T extends AliSmsEntity> {

    default IAcsClient getAcsClient() {
        return null;
    }



    default CommonRequest getAliSmsRequest(T t) {
        return null;
    }


}
