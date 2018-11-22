package com.fast.family.mvc.exception;

import com.fast.family.commons.exception.*;
import org.apache.ibatis.jdbc.SQL;
import org.apiguardian.api.API;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.general.GeneralAdviceTrait;
import org.zalando.problem.spring.web.advice.http.HttpAdviceTrait;
import org.zalando.problem.spring.web.advice.io.IOAdviceTrait;
import org.zalando.problem.spring.web.advice.routing.RoutingAdviceTrait;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

/**
 * @author 张顺
 * @version 1.0
 */
@API(status = API.Status.STABLE)
public interface ServiceAdviceTrait extends GeneralAdviceTrait, HttpAdviceTrait, IOAdviceTrait
        , RoutingAdviceTrait, SecurityAdviceTrait {


    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = ApiException.class)
    default ResponseEntity<Problem> handlerApiException(ApiException exception,
                                                        NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }

    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = GenericBusinessException.class)
    default ResponseEntity<Problem> handlerGenericBusinessException(GenericBusinessException exception,
                                                                    NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }

    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = SqlException.class)
    default ResponseEntity<Problem> handlerSqlException(SqlException exception,
                                                                    NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }

    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = NoAuthException.class)
    default ResponseEntity<Problem> handlerNoAuthException(NoAuthException exception,
                                                        NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }

    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = NoLoginException.class)
    default ResponseEntity<Problem> handlerNoLoginException(NoLoginException exception,
                                                        NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }

    @API(status = API.Status.INTERNAL)
    @ExceptionHandler(value = ValidateCodeException.class)
    default ResponseEntity<Problem> handlerValidateCodeException(ValidateCodeException exception,
                                                            NativeWebRequest request){
        return this.create(exception,
                ProblemUtils.createProblem(exception.getErrMessage(),
                        exception.getErrCode()),request);
    }
}
