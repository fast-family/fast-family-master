package com.fast.family.third.poarty.sms.ali;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fast.family.commons.exception.AliSmsException;
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
                aliSmsProperties.getAliyunAccessKeyId(), aliSmsProperties.getAliyunAccessKeySecret());
        try {
            DefaultProfile.addEndpoint(aliSmsProperties.getEndPointName(), aliSmsProperties.getRegionId(),
                    aliSmsProperties.getProduce(), aliSmsProperties.getDomain());
        } catch (ClientException e) {
            throw new AliSmsException(e);
        }
        return new DefaultAcsClient(profile);
    }

    @Override
    public CommonRequest getAliSmsRequest(T t) {
        CommonRequest request = new CommonRequest();
        request.setAction("SendSms");
        request.setVersion("2017-05-25");
        request.setMethod(MethodType.POST);
        request.setDomain(aliSmsProperties.getDomain());
        request.setProduct(aliSmsProperties.getProduce());
        request.setEndpointType(aliSmsProperties.getEndPointName());
        request.putQueryParameter("PhoneNumbers", t.getMobile());
        request.putQueryParameter("TemplateCode", t.getTemplateCode());
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(t.getParams()));
        request.putQueryParameter("SignName", t.getSignName());
        request.putQueryParameter("OutId", t.getOutId());
        return request;
    }
}
