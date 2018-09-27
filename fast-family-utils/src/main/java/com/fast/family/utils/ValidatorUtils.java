package com.fast.family.utils;




import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ValidatorUtils {

    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static List<ValidatorError> validatorEntity(Object object){
        List<ValidatorError> errorList = new ArrayList<>();
        Set<ConstraintViolation<Object>> constraintViolationSet  = validator.validate(object);
        if (!constraintViolationSet.isEmpty()){
            Iterator<ConstraintViolation<Object>> iterator = constraintViolationSet.iterator();
            while (iterator.hasNext()){
                ConstraintViolation<Object> constraintViolation = iterator.next();
                ValidatorError validatorError = new ValidatorError();
                validatorError.setErrorMsg(constraintViolation.getMessageTemplate());
                constraintViolation.getPropertyPath().forEach(path -> {
                    validatorError.setColumnName(path.getName());
                });
                errorList.add(validatorError);
            }
        }
        return errorList;
    }

    @Data
    public static class ValidatorError{

        private String columnName;

        private String errorMsg;

    }

}
