package edu.zut.handler.exception;

import edu.zut.domain.ResponseResult;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/10/8
 * Time: 17:13
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandle(SystemException se) {
        log.info("您的异常请注意查收：{}", se);
        return ResponseResult.errorResult(se.getCode(), se.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandle(Exception e) {
        log.info("您的异常请注意查收：{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}
