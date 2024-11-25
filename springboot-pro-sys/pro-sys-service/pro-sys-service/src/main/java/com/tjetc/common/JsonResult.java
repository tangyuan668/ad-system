package com.tjetc.common;

import lombok.Getter;

/**
 * 统一返回类型
 *
 * @param <T>反省，表示返回前端的数据类型
 */

public class JsonResult<T> {
    //状态码： -1 表示未登录或则和登录过期 0 表示业务上成功 1（大于0） 表示业务上失败
    private int state;
    //业务上失败信息
    private String message;
    //业务数据
    private T data;
//定义私有的构造方法，不允许使用new对象

    private JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> JsonResult success(String message, T data){

return new JsonResult(0,message,data);
    }
    //用T用E都行
    public static <E> JsonResult success(E data){

        return new JsonResult(0,null,data);
    }
    public static JsonResult fail(String message)
    {
        return new JsonResult(1,message,null);

    }
    public static <T> JsonResult fail(String message,T data)
    {
        return new JsonResult(1,message,data);

    }
    public static  JsonResult fail(int state,String message)
    {
        return new JsonResult(state,message,null);

    }


}
