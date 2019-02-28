package com.fast.family.third.poarty.sms.ali;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class DefaultAliSmsTemplate extends AbstractAliSmsTemplate<AliSmsEntity> {

    public DefaultAliSmsTemplate(AliSmsProperties aliSmsProperties) {
        super(aliSmsProperties);
    }

    @Override
    public void sendSms(AliSmsEntity aliSmsEntity) {
        IAcsClient acsClient = getAcsClient();
        SendSmsRequest request = getAliSmsRequest(aliSmsEntity);
        SendSmsResponse response = null;

        try {
            response = acsClient.getAcsResponse(request);
            if (response.getCode() == null
                    || !response.getCode().equalsIgnoreCase("OK")) {
                throw new AliSmsException(response.getMessage());
            }
        } catch (ClientException e) {
            throw new AliSmsException(e);
        }
    }
}
