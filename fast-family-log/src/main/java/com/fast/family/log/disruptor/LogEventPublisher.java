package com.fast.family.log.disruptor;

import com.fast.family.log.AccessLogInfo;
import com.fast.family.log.repository.LogRepository;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
@Slf4j
public class LogEventPublisher implements DisposableBean, InitializingBean {

    private Disruptor<LogEvent> disruptor;

    private LogRepository logRepository;

    public LogEventPublisher(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void start() {
        disruptor = new Disruptor<LogEvent>(new LogEventFactory(), 1024, r -> {
            AtomicInteger integer = new AtomicInteger(1);
            return new Thread(null, r, "disruptor-thread-" + integer.getAndIncrement());
        }, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new LogEventHandler(logRepository));
        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.start();
    }

    public void publishEvent(AccessLogInfo accessLogInfo) {
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(new LogEventTranslator(), accessLogInfo);
    }

    @Override
    public void destroy() throws Exception {
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}
