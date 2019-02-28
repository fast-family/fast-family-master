package com.fast.family.mvc.exception;

import com.fast.family.commons.exception.*;
import com.fast.family.commons.json.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@Slf4j
@RestControllerAdvice
public class GlobalHandlerExceptionResolver {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //验证requestbody失败异常的处理
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logError("methodArgumentNotValidException", e.getMessage(), e);
        return Response.fail(400,
                methodArgumentNotValidExceptionFirstErrorMessage(e.getBindingResult()));
    }




    static String methodArgumentNotValidExceptionFirstErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().findFirst()
                .map(ObjectError::getDefaultMessage).orElse("");
    }




    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    //对于接口参数requestParam的validate验证
    public Response handleConstraintViolationException(ConstraintViolationException e) {
        logError("constraintViolationException", e.getMessage(), e);
        return Response.fail(400,
                constraintViolationExceptionFirstErrorMessage(e.getConstraintViolations()));
    }



    static String constraintViolationExceptionFirstErrorMessage(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().findFirst()
                .map(constraintViolation -> constraintViolation.getPropertyPath().toString() + ":"
                        + constraintViolation.getMessage()).orElse("");
    }



    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        logError("missingServletRequestParameterException", e.getMessage(), e);
        return Response
                .fail(400,
                        String.format("参数%s未传", e.getParameterName()));
    }




    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) {
        logError("httpMediaTypeNotSupportedException", e.getMessage(), e);
        return Response
                .fail(415,
                        String.format("媒体类型%s错误", e.getContentType()));
    }




    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    //对于接口方法不匹配的异常处理
    public Response handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        logError("httpRequestMethodNotSupportedException", e.getMessage(), e);
        return Response
                .fail(405,
                        e.getMessage());
    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public Response handlerApiException(ApiException t){
        logError("handlerApiException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(GenericBusinessException.class)
    public Response hanlderGenericBusinessException(GenericBusinessException t){
        logError("handlerGenericBusinessException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(SqlException.class)
    public Response handlerSqlException(SqlException t){
        logError("handlerSqlException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NoAuthException.class)
    public Response handlerNoAuthException(NoAuthException t){
        logError("handlerNoAuthException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getMessage());
    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NoLoginException.class)
    public Response handlerNoLoginException(NoLoginException t){
        logError("handlerNoLoginException",t.getMessage(),t);
        return Response.fail(t.getErrCode(),t.getMessage());
    }




    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Response handlerRuntimeException(RuntimeException t){
        logError("handlerRuntimeException",t.getMessage(),t);
        return Response.fail(500,t.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Response handlerThrowable(Throwable t){
        logError("throwable error",t.getMessage(),t);
        return Response.fail(500,t.getMessage());
    }



    private static void logError(String name,String msg,Throwable throwable){
        log.error("error name is {},msg is {}",name,msg,throwable);
    }



}
