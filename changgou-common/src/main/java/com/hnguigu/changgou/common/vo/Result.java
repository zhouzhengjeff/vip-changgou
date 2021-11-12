package com.hnguigu.changgou.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1989477363042141450L;

    private Boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
