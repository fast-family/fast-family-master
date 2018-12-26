package com.fast.family.mvc.validate.constraint;

import com.fast.family.mvc.validate.annotation.*;
import com.google.common.collect.Maps;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public class ConstraintHelper {

    private static final Map<String,Constraint> CONSTRAINT_MAP = Maps.newConcurrentMap();

    static {
        CONSTRAINT_MAP.put(AssertFalse.class.getName(),new ConstraintAssertFalse());
        CONSTRAINT_MAP.put(AssertTrue.class.getName(),new ConstraintAssertTrue());
        CONSTRAINT_MAP.put(CollectionSize.class.getName(),new ConstraintCollectionSize());
        CONSTRAINT_MAP.put(DateFormat.class.getName(),new ConstraintDateFormat());
        CONSTRAINT_MAP.put(DecimalMax.class.getName(),new ConstraintDecimalMax());
        CONSTRAINT_MAP.put(DecimalMin.class.getName(),new ConstraintDecimalMin());
        CONSTRAINT_MAP.put(Digits.class.getName(),new ConstraintDigits());
        CONSTRAINT_MAP.put(Future.class.getName(),new ConstraintFuture());
        CONSTRAINT_MAP.put(Max.class.getName(),new ConstraintMax());
        CONSTRAINT_MAP.put(Min.class.getName(),new ConstraintMin());
        CONSTRAINT_MAP.put(NotBlank.class.getName(),new ConstraintNotBlank());
        CONSTRAINT_MAP.put(NotEmpty.class.getName(),new ConstraintNotEmpty());
        CONSTRAINT_MAP.put(NotNull.class.getName(),new ConstraintNotNull());
        CONSTRAINT_MAP.put(Null.class.getName(),new ConstraintNull());
        CONSTRAINT_MAP.put(Past.class.getName(),new ConstraintPast());
        CONSTRAINT_MAP.put(Size.class.getName(),new ConstraintSize());
    }


    public static Constraint getConstraint(Annotation annotation){
        return CONSTRAINT_MAP.get(annotation.getClass().getName());
    }
}
