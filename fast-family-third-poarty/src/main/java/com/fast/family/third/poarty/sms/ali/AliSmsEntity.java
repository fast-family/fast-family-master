package com.fast.family.third.poarty.sms.ali;

import com.fast.family.third.poarty.sms.SmsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AliSmsEntity extends SmsEntity {

    private String signName;

    private String outId;
}
