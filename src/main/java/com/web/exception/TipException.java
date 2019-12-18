package com.web.exception;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/30 9:48
 * @describe 统一定义的RuntimeException异常
 */
public class TipException extends RuntimeException {
    public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }
}
