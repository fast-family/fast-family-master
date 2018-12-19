package com.fast.family.log.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author 张顺
 * @version 1.0
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
