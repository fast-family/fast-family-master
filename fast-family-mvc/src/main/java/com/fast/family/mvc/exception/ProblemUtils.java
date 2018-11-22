package com.fast.family.mvc.exception;

import org.slf4j.MDC;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * @author 张顺
 * @version 1.0
 */
public interface ProblemUtils {

    static Problem success(){
        return createProblem("success");
    }


    static Problem createProblem(Object data){
        String traceId = MDC.get("traceId");
        if (traceId != null){
            return Problem.builder().withDetail("success").with("code",200)
                    .with("data",data).with("traceId",traceId).withStatus(Status.OK).build();
        } else {
            return Problem.builder().withDetail("success").with("code",200)
                    .with("data",data).withStatus(Status.OK).build();
        }
    }

    static Problem createProblem(String detail,Object data){
        String traceId = MDC.get("traceId");
        if (traceId != null){
            return Problem.builder().withDetail(detail).with("code",200)
                    .with("data",data).with("traceId",traceId).withStatus(Status.OK).build();
        } else {
            return Problem.builder().withDetail(detail).with("code",200)
                    .with("data",data).with("traceId",traceId).withStatus(Status.OK).build();
        }
    }

    static Problem createProblem(String detail,int code){
        String traceId = MDC.get("traceId");
        if (traceId != null){
            return Problem.builder().withDetail(detail).with("traceId",traceId)
                    .withStatus(Status.OK).build();
        } else {
            return Problem.builder().withDetail(detail).with("code",code).withStatus(Status.OK).build();
        }
    }

}
