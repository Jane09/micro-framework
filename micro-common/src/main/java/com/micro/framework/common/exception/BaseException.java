package com.micro.framework.common.exception;

/**
 * 基础异常
 * @author tb
 * @date 2018/12/27 17:09
 */
public class BaseException extends RuntimeException {


    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
