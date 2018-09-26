package com.fast.family.mvc.exception;

import com.fast.family.commons.exception.ApiException;
import com.fast.family.commons.exception.GenericBusinessException;
import com.fast.family.commons.exception.SqlException;
import com.fast.family.commons.json.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalHandlerExceptionResolver {


    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(SqlException.class)
    public Response handlerSqlException(SqlException t){
        logError("handlerSqlException",t.getMessage(),t);
        return Response.fail(503,"SqlException error",t.getMessage());
    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public Response hanlderControllerException(ApiException t){
        logError("hanlderControllerException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getErrMessage(),t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(GenericBusinessException.class)
    public Response handlerGenericBusinessException(GenericBusinessException t){
        logError("handlerGenericBusinessException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getErrMessage(),t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Response handlerRuntimeException(RuntimeException t){
        logError("handlerRuntimeException",t.getMessage(),t);
        return Response.fail(500,"RuntimeException error",t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Response handlerThrowable(Throwable t){
        logError("handlerThrowable",t.getMessage(),t);
        return Response.fail(500,"throwable error",t.getMessage());
    }

    private static void logError(String name,String msg,Throwable t){
        log.error("error name is {},msg is {}",name,msg,t);
    }
}
