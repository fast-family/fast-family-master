package com.fast.family.third.poarty.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.fast.family.third.poarty.pay.ali.AlipayProperties;
import com.fast.family.third.poarty.pay.ali.service.AlipayWapService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class AlipayWapServiceImpl implements AlipayWapService {

    private AlipayProperties aliPayProperties;

    private AlipayClient alipayClient;

    public AlipayWapServiceImpl(AlipayProperties aliPayProperties, AlipayClient alipayClient) {
        this.aliPayProperties = aliPayProperties;
        this.alipayClient = alipayClient;
    }

    @Override
    public AlipayTradeWapPayResponse payWap(AlipayTradeWapPayModel model) throws AlipayApiException {
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(aliPayProperties.getNotifyUrl());
        request.setReturnUrl(aliPayProperties.getReturnUrl());
        return alipayClient.pageExecute(request);
    }
}
