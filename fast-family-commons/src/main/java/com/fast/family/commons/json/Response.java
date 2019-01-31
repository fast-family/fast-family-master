package com.fast.family.commons.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String message;

    private int code;

    private T data;

    public static <T>Response<T> ok(){
        return ok(200,"success",null);
    }

    public static <T>Response<T> ok(T data){
        return ok(200,"success",data);
    }

    public static <T>Response<T> ok(int code,String message,T data){
        Response response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static <T>Response<T> fail(){
        return fail(500,"runtimeException");
    }

    public static <T>Response<T> fail(int code,String message){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
