package com.fast.family.commons.converters;

import com.fast.family.commons.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class StringToDateConverter extends StringGenericConverter<Date> {

    public StringToDateConverter(){
        super(Date.class);
    }

    @Override
    protected Date doConvert(String source) {
        return DateTimeUtils.convertLDTToDate(source);
    }
}
