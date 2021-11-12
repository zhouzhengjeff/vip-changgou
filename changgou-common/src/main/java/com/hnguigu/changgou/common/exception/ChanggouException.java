package com.hnguigu.changgou.common.exception;

public class ChanggouException extends RuntimeException {

    private String message;

    public ChanggouException() {
    }

    public ChanggouException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
