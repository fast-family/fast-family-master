package com.fast.family.commons.converters;

/**
 * @author 张顺
 * @version 1.0
 */
public class StringToBooleanConverter extends StringGenericConverter<Boolean> {


    public StringToBooleanConverter(){
        super(Boolean.FALSE,Boolean.class);
    }

    protected StringToBooleanConverter(Class<Boolean> targetType) {
        super(targetType);
    }

    public StringToBooleanConverter(Boolean defaultValue, Class<Boolean> targetType) {
        super(defaultValue, targetType);
    }

    @Override
    protected Boolean doConvert(String source) {
        return null;
    }
}
