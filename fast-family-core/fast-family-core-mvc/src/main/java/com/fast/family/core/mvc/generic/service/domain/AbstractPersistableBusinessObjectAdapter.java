package com.fast.family.core.mvc.generic.service.domain;

import com.fast.family.mvc.generic.entity.GenericEntity;

public abstract class AbstractPersistableBusinessObjectAdapter<T extends GenericEntity>
        extends AbstractPersistableBusinessObject<T> {

    public AbstractPersistableBusinessObjectAdapter(Class<T> persistableEntityClass) {
        super(persistableEntityClass);
    }

    protected void customizeConvert(T persistableEntity) {
    }

    protected void customizeFill(T persistableEntity) {
    }
}
