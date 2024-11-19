package com.example.demo.common;

import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.exception.RetCode;
import lombok.Data;

@Data
public class RetResult<T> {
    private int code;
    private String message;
    private T data;

    public RetResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RetResult() {
    }

    public static <T> RetResult<T> error(BusinessException e) {
        RetResult<T> retResult = new RetResult<>();
        retResult.setCode(e.getCode());
        retResult.setMessage(e.getMessage());
        return retResult;
    }

    public static <T> RetResult<T> error(RetCode retCode, Object... parameters) {
        return new RetResult<>(retCode, null, parameters);
    }


    public static <T> RetResult<T> success() {
        return new RetResult<>(RetCode.SUCCESS, null);
    }

    public static <T> RetResult<T> success(T data) {
        return new RetResult<>(RetCode.SUCCESS, data);
    }

    public RetResult(RetCode retCode, T data, Object... parameters) {
        this.code = retCode.getCode();
        this.message = String.format(retCode.getMessage(), parameters);
        this.data = data;
    }

}
