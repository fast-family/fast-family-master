package com.fast.family.commons.converters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Set;

/**
 * @author 张顺
 * @version 1.0
 */
public abstract class StringGenericConverter<T> implements GenericConverter {


    @Getter
    @Setter
    private T defaultValue;

    @Getter
    private final Class<T> targetType;

    protected StringGenericConverter(Class<T> targetType) {
        this(null,targetType);
    }

    protected StringGenericConverter(T defaultValue, Class<T> targetType) {
        this.defaultValue = defaultValue;
        this.targetType = targetType;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class,targetType));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (ObjectUtils.isEmpty(source)){
            return defaultValue;
        }
        try {
            return doConvert(source.toString().trim());
        } catch (Exception e){
            throw new ConversionFailedException(TypeDescriptor.forObject(source),
                    TypeDescriptor.valueOf(this.targetType),source,e);
        }
    }

    protected abstract T doConvert(String source);
}
