package com.core.exception;

/**
 * 验证码校验异常
 *
 * @author xiaoMing
 * Create on 2020-08-12.
 */
public class ValidateCodeException extends RuntimeException {

    private static final long serialVersionUID = 8798176161238427050L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
