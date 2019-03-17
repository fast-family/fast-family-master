package com.fast.family.commons.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 张顺
 * @version 1.0
 */
public class StringToLocalDateConverter extends StringGenericConverter<LocalDate> {

    public StringToLocalDateConverter(){
        super(LocalDate.class);
    }

    @Override
    protected LocalDate doConvert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
