package com.fast.family.log.disruptor;

import com.fast.family.log.repository.LogRepository;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LogEventHandler implements  WorkHandler<LogEvent> {


    private final LogRepository logRepository;

    public LogEventHandler(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @Override
    public void onEvent(LogEvent event) throws Exception {
        logRepository.save(event.getAccessLogInfo());
        event.clear();
    }
}
