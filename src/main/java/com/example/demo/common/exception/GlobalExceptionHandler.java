package com.example.demo.common.exception;

import com.example.demo.common.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public RetResult<Void> handleBusinessException(BusinessException e) {
        log.error("【业务异常】：", e);
        return RetResult.error(e);
    }

    @ExceptionHandler(Exception.class)
    public RetResult<Void> handleException(Exception e) {
        if (e instanceof UndeclaredThrowableException) {
            // 处理 SneakyThrows
            if (e.getCause() instanceof BusinessException) {
                return handleBusinessException((BusinessException) (e.getCause()));
            }
        }
        log.error("【服务器异常】：", e);
        return RetResult.error(RetCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public RetResult<Void> handleError(Throwable e) {
        log.error("【系统错误】：", e);
        return RetResult.error(RetCode.BAD_REQUEST, e.getMessage());
    }
}
