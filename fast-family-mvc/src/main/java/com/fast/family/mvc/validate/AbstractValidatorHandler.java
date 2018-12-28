package com.fast.family.mvc.validate;

import com.fast.family.mvc.validate.constraint.Constraint;
import com.fast.family.mvc.validate.constraint.ConstraintHelper;
import com.fast.family.mvc.validate.exception.ValidateException;
import com.fast.family.mvc.validate.interceptor.DefaultMessageInterceptor;
import com.fast.family.mvc.validate.interceptor.MessageInterceptor;
import com.google.common.collect.Lists;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
@Valid
public  class AbstractValidatorHandler<T extends Annotation,K> implements Validator<T,K>{

    private List<MessageInterceptor> messageInterceptorList = Lists.newArrayList();

    private List<Constraint> constraintList;

    public AbstractValidatorHandler() {
        messageInterceptorList.add(new DefaultMessageInterceptor());
    }

    @Override
    public ValidateResponse validate(Annotation[] annotations){
        ValidateResponse validateResponse = new ValidateResponse();

        for (Annotation annotation : annotations){
            messageInterceptorList.forEach(r -> r.before());
            Constraint constraint = ConstraintHelper.getConstraint(annotation);
//            constraint.restrict(annotation.getClass().getFields(),);
            messageInterceptorList.forEach(r -> r.after());
        }

//        constraintList.forEach(c -> {
//            try {
//                c.restrict(null,null);
//            } catch (ValidateException e){
//                validateResponse.addErrorMsg(ValidateErrorMsg.builder()
//                        .errorMsg(e.getErrMsg())
//                        .name(e.getName()).build());
//            }
//        });

        return validateResponse;
    }

    public void addMessageInterceptor(MessageInterceptor messageInterceptor){
        messageInterceptorList.add(messageInterceptor);
    }




}
