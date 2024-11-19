package com.example.demo.common.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    protected int code;
    protected String message;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(RetCode retCode, Object... parameters) {
        this.code = retCode.getCode();
        this.message = String.format(retCode.getMessage(), parameters);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
