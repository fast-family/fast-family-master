package com.fast.family.commons.converters;

import com.fast.family.commons.utils.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * @author 张顺
 * @version 1.0
 */
public class StringToLocalDateTimeConverter extends StringGenericConverter<LocalDateTime> {

    public StringToLocalDateTimeConverter(){
        super(LocalDateTime.class);
    }

    @Override
    protected LocalDateTime doConvert(String source) {
        return DateTimeUtils.parserDateTime(source);
    }
}
