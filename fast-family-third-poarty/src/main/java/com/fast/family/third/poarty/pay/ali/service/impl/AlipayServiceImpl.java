package com.fast.family.third.poarty.pay.ali.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.fast.family.third.poarty.pay.ali.AlipayProperties;
import com.fast.family.third.poarty.pay.ali.service.AlipayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlipayServiceImpl implements AlipayService {

    private AlipayProperties aliPayProperties;


    private AlipayClient alipayClient;

    public AlipayServiceImpl(AlipayProperties aliPayProperties, AlipayClient alipayClient) {
        this.aliPayProperties = aliPayProperties;
        this.alipayClient = alipayClient;
    }

    @Override
    public AlipayTradeRefundResponse refund(AlipayTradeRefundModel model) throws AlipayApiException {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.setBizModel(model);
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
            return alipayClient.execute(request);

    }

    @Override
    public AlipayTradeFastpayRefundQueryResponse selectRefund(AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException {
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            request.setBizModel(model);
            return alipayClient.execute(request);

    }

    @Override
    public AlipayTradeQueryResponse selectOrder(AlipayTradeQueryModel model) throws AlipayApiException {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizModel(model);
            return alipayClient.execute(request);
    }

    @Override
    public AlipayDataDataserviceBillDownloadurlQueryResponse downBill(
            AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizModel(model);
        return alipayClient.execute(request);

    }
}
