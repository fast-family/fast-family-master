package com.fast.family.mvc.generic.service.domain;

import com.fast.family.mvc.generic.entity.GenericEntity;
import com.fast.family.utils.BeanUtils;
import com.fast.family.utils.ReflectionUtils;

public abstract class AbstractPersistableBusinessObject<T extends GenericEntity>
        implements BusinessObject{

    private final Class<T> persistableEntityClass;


    public AbstractPersistableBusinessObject(Class<T> persistableEntityClass) {
        this.persistableEntityClass = persistableEntityClass;
    }

    public final T convertTo(){
        T result = ReflectionUtils.newInstance(persistableEntityClass);
        BeanUtils.copy(this,result);
        customizeConvert(result);
        return result;
    }

    public final void fillFrom(final T persistableEntity){
        BeanUtils.copyPropertiesIgnoreNull(persistableEntity,this);
        customizeFill(persistableEntity);
    }


    protected abstract void customizeConvert(T t);

    protected abstract void customizeFill(T t);
}
