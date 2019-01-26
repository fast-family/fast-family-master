package com.fast.family.third.poarty.pay.ali.service;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.response.AlipayTradeAppPayResponse;


/**
 * @author 张顺
 * @version 1.0
 */
public interface AlipayAppService {

    AlipayTradeAppPayResponse createOrder(AlipayTradeAppPayModel model) throws AlipayApiException;
}
