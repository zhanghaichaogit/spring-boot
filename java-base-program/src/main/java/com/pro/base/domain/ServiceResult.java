package com.pro.base.domain;


import java.io.Serializable;

/**
 * 响应的结果对象类
 */
public class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = -8824585616330663966L;
    /**
     * 返回给客户端的结果
     */
    private T result;

    /**
     * 返回给客户端的消息描述
     */
    private String message;

    /**
     * 返回给客户端的消息代码
     */
    private String code;

    /**
     * 执行成功标志
     */
    private boolean success = false;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
