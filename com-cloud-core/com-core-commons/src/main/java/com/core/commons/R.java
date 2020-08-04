package com.core.commons;

import com.core.commons.constants.RConstant;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-02.
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = -6990698642175651419L;

    private String msg = "success";
    private int code = 0;
    private long retTime = System.currentTimeMillis();
    private T data;

    public R() {
        super();
    }

    public R<T> success() {
        this.code = RConstant.SUCCESS;
        return this;
    }

    public R<T> success(String msg) {
        this.code = RConstant.SUCCESS;
        this.msg = msg;
        return this;
    }

    public R<T> failure() {
        this.code = RConstant.FAIL;
        return this;
    }

    public R<T> failure(String msg) {
        this.code = RConstant.FAIL;
        this.msg = msg;
        return this;
    }

    public R<T> failure(Throwable e) {
        this.msg = e.getMessage();
        this.code = RConstant.FAIL;
        return this;
    }

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public static R<String> of(final String msg, final int code) {
        return new R<String>().code(code).failure(msg);
    }
}
