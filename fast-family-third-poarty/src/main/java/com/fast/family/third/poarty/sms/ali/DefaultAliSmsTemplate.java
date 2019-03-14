package com.fast.family.third.poarty.sms.ali;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.fast.family.commons.exception.AliSmsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
        final IAcsClient acsClient = this.getAcsClient();
        final CommonRequest request = this.getAliSmsRequest(aliSmsEntity);
        final CommonResponse response;
        try {
            response = acsClient.getCommonResponse(request);
            this.parseResponse(response);
        } catch (ClientException | IOException e) {
            throw new AliSmsException(e);
        } finally {
            acsClient.shutdown();
        }
    }

    private void parseResponse(final CommonResponse response) throws IOException {
        log.debug("response : {}", response.getData());
        final AliSmsReponse smsReponse = new ObjectMapper().readValue(response.getData(), AliSmsReponse.class);
        if (response.getHttpStatus() != 200 || !"OK".equals(smsReponse.getCode())) {
            log.error("发送短信失败，状态码：{}，返回数据：{}", response.getHttpStatus(), smsReponse);
            throw new AliSmsException(smsReponse.getMessage());
        }
    }
}
