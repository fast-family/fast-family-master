package com.fast.family.third.poarty.pay.ali.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;


public interface AlipayService {


    /**
     * 退款
     * @param model
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeRefundResponse refund(AlipayTradeRefundModel model) throws AlipayApiException;


    /**
     * 查询退款
     * @param model
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeFastpayRefundQueryResponse selectRefund(AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException;

    /**
     * 查询订单
     * @param model
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeQueryResponse selectOrder(AlipayTradeQueryModel model) throws AlipayApiException;


    /**
     * 下载账单
     * @param model
     * @return
     * @throws AlipayApiException
     */
    AlipayDataDataserviceBillDownloadurlQueryResponse downBill(AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException;
}
