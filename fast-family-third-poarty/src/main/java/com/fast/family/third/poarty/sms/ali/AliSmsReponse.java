package com.fast.family.third.poarty.sms.ali;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Vincent Kang
 * <pre>
 *
 * </pre>
 * @version 0.1.0
 * @date 2019/3/7-11:16
 */
@Data
public class AliSmsReponse {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("BizId")
    private String bizId;

    @JsonProperty("Message")
    private String message;

}
