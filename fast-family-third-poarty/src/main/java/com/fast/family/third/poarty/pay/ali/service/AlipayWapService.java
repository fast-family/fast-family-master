package com.fast.family.third.poarty.pay.ali.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayTradeWapPayResponse;

/**
 * @author 张顺
 * @version 1.0
 */
public interface AlipayWapService {

    /**
     * 网页支付
     * @param model
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeWapPayResponse payWap(AlipayTradeWapPayModel model) throws AlipayApiException;

}
