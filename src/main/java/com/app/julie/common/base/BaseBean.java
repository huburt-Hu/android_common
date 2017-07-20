package com.app.julie.common.base;

/**
 * Created by julie on 2017/6/30.
 */
public class BaseBean<T> {

    public static final int CODE_OK = 200;
    public static final int CODE_ERROR = 400;

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
