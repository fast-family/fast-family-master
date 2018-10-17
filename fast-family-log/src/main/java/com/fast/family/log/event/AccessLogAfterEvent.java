package com.fast.family.log.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-9:56
 */
@Slf4j
public class AccessLogAfterEvent extends ApplicationEvent {

    public AccessLogAfterEvent(Object source) {
        super(source);
    }
}
