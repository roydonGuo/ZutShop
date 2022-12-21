package edu.zut.handler.exception;

import edu.zut.constants.AppHttpCodeEnum;
import edu.zut.domain.ResponseResult;
import edu.zut.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandle(SystemException se) {
        log.info("最喜欢异常了==>{}", se);
        return ResponseResult.errorResult(se.getCode(), se.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandle(Exception e) {
        log.info("最喜欢异常了==>{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}
