package com.lh.order.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@ControllerAdvice
@ResponseBody*/
//全局异常处理器
@RestControllerAdvice
public class GlobalExceptinoHandler {

    @ExceptionHandler(value = Throwable.class)
    public String error(Throwable e) {
        System.out.println("全局》》》》》》》》》》》》》》》》》。"+e.getMessage());
        return e.getMessage();
    }
}
