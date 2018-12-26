package com.fast.family.mvc.validate;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class ValidateResponse {

    private List<ValidateErrorMsg> errorMsgList = Lists.newArrayList();

    public void addErrorMsg(ValidateErrorMsg validateErrorMsg){
        errorMsgList.add(validateErrorMsg);
    }
}
