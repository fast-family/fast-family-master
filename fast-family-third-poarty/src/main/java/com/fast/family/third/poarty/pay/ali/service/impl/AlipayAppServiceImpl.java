package com.fast.family.third.poarty.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fast.family.third.poarty.pay.ali.service.AlipayAppService;


/**
 * @author 张顺
 * @version 1.0
 */

public class AlipayAppServiceImpl implements AlipayAppService {


    private AlipayClient alipayClient;

    public AlipayAppServiceImpl(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    @Override
    public AlipayTradeAppPayResponse createOrder(AlipayTradeAppPayModel model) throws AlipayApiException {
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setBizModel(model);
            request.setNotifyUrl("");
            return alipayClient.sdkExecute(request);

    }
}
