package com.fast.family.log.disruptor;

import com.fast.family.log.AccessLogInfo;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LogEventHandler implements EventHandler<AccessLogInfo>, WorkHandler<AccessLogInfo> {

    /**
     *
     * @param event 日志信息
     * @param sequence 序列号
     * @param endOfBatch 是否结束
     * @throws Exception
     */
    @Override
    public void onEvent(AccessLogInfo event, long sequence, boolean endOfBatch) throws Exception {

    }

    @Override
    public void onEvent(AccessLogInfo event) throws Exception {

    }
}
