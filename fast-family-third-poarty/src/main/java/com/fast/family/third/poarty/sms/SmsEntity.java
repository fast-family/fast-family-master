package com.fast.family.third.poarty.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsEntity implements Serializable {

    private String templateCode;

    private String mobile;

    private String time;


}
