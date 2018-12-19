package com.fast.family.log.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LogEventPublisher implements DisposableBean {

    private Disruptor<LogEvent> disruptor;

    @Override
    public void destroy() throws Exception {
        disruptor.shutdown();
    }
}
