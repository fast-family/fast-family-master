package com.fast.family.log.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LogEventHandlerException implements ExceptionHandler {


    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
