package com.core.exception;

/**
 * 全局check检查异常
 * @author xiaoMing
 * Create on 2020-08-18.
 */
public class CheckedException extends RuntimeException {

    private static final long	serialVersionUID	= -7347917836329083340L;

    public CheckedException() {
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
