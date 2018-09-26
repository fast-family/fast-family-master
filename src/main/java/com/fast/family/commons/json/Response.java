package com.fast.family.commons.json;

import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:47
 */
@Data
public class Response<T> {

    private int code = 200;
    private String message;
    private T data;
    private String detailMessage;


    public static <T> Response<T> ok(){
        return new Response<>();
    }

    public static <T> Response<T> ok(T t){
        return ok(t,200,"success");
    }

    public static <T> Response<T> ok(T t,int code, String message){
        Response<T> Response = new Response<>();
        Response.setCode(code);
        Response.setData(t);
        Response.setMessage(message);
        return Response;
    }

    public static <T> Response<T> fail(String message){
        return fail(500,message,message);
    }

    public static <T> Response<T> fail(int code, String message, String detailMessage){
        Response<T> Response = new Response<>();
        Response.setCode(code);
        Response.setMessage(message);
        Response.setDetailMessage(detailMessage);
        return Response;
    }
}
