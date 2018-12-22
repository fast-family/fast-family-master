package com.fast.family.log.disruptor;

import com.fast.family.log.AccessLogInfo;
import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class LogEvent {

    private AccessLogInfo accessLogInfo;

    public void clear(){
        accessLogInfo = null;
    }
}
