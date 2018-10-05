package com.fast.family.commons.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/20-23:47
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    @JsonView(ResponseView.class)
    private int code = 200;

    @JsonView(ResponseView.class)
    private String message;//用于给用户展示

    @JsonView(ResponseView.class)
    private T data;

    @JsonView(ResponseView.class)
    private String detailMessage;//用于内部定位错误

    interface ResponseView{

    }


    public static <T> Response<T> ok(){
        return ok(null);
    }

    public static <T> Response<T> ok(T t){
        return ok(t,ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
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
