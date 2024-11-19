package com.example.demo.common.exception;

public enum RetCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request: %s"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error: %s");

    private int code;
    private String message;

    RetCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
