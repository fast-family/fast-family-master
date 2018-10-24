package com.fast.family.third.poarty.sms.ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.third.poarty.sms.AbstractSmsTemplate;


/**
 * @author 张顺
 * @version 1.0
 */
public abstract class AbstractAliSmsTemplate<T extends AliSmsEntity>
        extends AbstractSmsTemplate<T> implements AliSmsTemplate<T> {

    private final AliSmsProperties aliSmsProperties;

    public AbstractAliSmsTemplate(AliSmsProperties aliSmsProperties) {
        this.aliSmsProperties = aliSmsProperties;
    }

    @Override
    public IAcsClient getAcsClient() {
        IClientProfile profile = DefaultProfile.getProfile(aliSmsProperties.getRegionId(),
                aliSmsProperties.getAliyunAccessKeyId(),aliSmsProperties.getAliyunAccessKeySecret());
        try {
            DefaultProfile.addEndpoint(aliSmsProperties.getEndPointName(),aliSmsProperties.getRegionId(),
                    aliSmsProperties.getProduce(),aliSmsProperties.getDomain());
        } catch (ClientException e) {
            throw new AliSmsException(e);
        }
        return new DefaultAcsClient(profile);
    }

    @Override
    public SendSmsRequest getAliSmsRequest(T t) {
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(t.getMobile());
        request.setSignName(t.getSignName());
        request.setTemplateCode(t.getTemplateCode());
        request.setTemplateParam(GsonUtils.toJson(t,t.getClass()));
        request.setOutId(t.getOutId());
        return request;
    }
}
