package com.lh.dto.common;


import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static Result ok() {
        Result result = new Result();
        result.setCode(200);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(500);
        return result;
    }


    public static Result ok(int code,String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(int code,String msg) {
        return ok(code, msg);
    }
}
