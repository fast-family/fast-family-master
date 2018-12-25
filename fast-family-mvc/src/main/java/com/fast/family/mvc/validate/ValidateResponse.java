package com.fast.family.mvc.validate;

import lombok.Data;

import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class ValidateResponse {

    private List<ValidateErrorMsg> errorMsgList;
}
