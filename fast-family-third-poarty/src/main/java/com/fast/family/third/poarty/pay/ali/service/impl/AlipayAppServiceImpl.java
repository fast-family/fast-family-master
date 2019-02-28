package com.fast.family.third.poarty.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fast.family.third.poarty.pay.ali.AlipayProperties;
import com.fast.family.third.poarty.pay.ali.service.AlipayAppService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class AlipayAppServiceImpl implements AlipayAppService {


    private AlipayClient alipayClient;

    private AlipayProperties aliPayProperties;

    public AlipayAppServiceImpl(AlipayProperties aliPayProperties, AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
        this.aliPayProperties = aliPayProperties;
    }

    @Override
    public AlipayTradeAppPayResponse createOrder(AlipayTradeAppPayModel model) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(aliPayProperties.getNotifyUrl());
        return alipayClient.sdkExecute(request);

    }
}
