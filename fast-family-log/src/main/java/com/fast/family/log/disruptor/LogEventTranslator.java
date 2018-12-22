package com.fast.family.log.disruptor;

import com.fast.family.log.AccessLogInfo;
import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @author 张顺
 * @version 1.0
 */
public class LogEventTranslator implements EventTranslatorOneArg<LogEvent, AccessLogInfo> {

    @Override
    public void translateTo(LogEvent event, long sequence, AccessLogInfo logInfo) {
        event.setAccessLogInfo(logInfo);
    }
}
