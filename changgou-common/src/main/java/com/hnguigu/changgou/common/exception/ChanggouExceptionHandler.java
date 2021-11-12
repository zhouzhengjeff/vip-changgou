package com.hnguigu.changgou.common.exception;

import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ChanggouExceptionHandler {

    @ExceptionHandler(value = ChanggouException.class)
    @ResponseBody
    public Result handleException(Exception exception) {
        return new Result(false, StatusCode.ERROR, exception.getMessage(), null);
    }
}
