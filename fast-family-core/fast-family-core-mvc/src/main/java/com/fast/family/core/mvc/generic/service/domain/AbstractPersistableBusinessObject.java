package com.fast.family.core.mvc.generic.service.domain;

import com.fast.family.commons.utils.BeanUtils;
import com.fast.family.commons.utils.ReflectionUtils;
import com.fast.family.mvc.generic.entity.GenericEntity;


public abstract class AbstractPersistableBusinessObject<T extends GenericEntity>
        implements BusinessObject {

    private final Class<T> persistableEntityClass;


    public AbstractPersistableBusinessObject(Class<T> persistableEntityClass) {
        this.persistableEntityClass = persistableEntityClass;
    }

    public final T convertTo() {
        T result = ReflectionUtils.newInstance(persistableEntityClass);
        BeanUtils.copy(this, result);
        customizeConvert(result);
        return result;
    }

    public final void fillFrom(final T persistableEntity) {
        BeanUtils.copy(persistableEntity, this);
        customizeFill(persistableEntity);
    }


    protected abstract void customizeConvert(T t);

    protected abstract void customizeFill(T t);
}
