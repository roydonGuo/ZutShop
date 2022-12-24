package edu.zut.handler.exception;

import edu.zut.domain.ResponseResult;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获，打印在控制台
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     * @param se SystemException
     * @return errorResult
     */
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandle(SystemException se) {
        log.info("最喜欢异常了==>{}", se);
        return ResponseResult.errorResult(se.getCode(), se.getMessage());
    }

    /**
     * 系统异常
     * @param e Exception
     * @return errorResult
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandle(Exception e) {
        log.info("最喜欢异常了==>{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}
