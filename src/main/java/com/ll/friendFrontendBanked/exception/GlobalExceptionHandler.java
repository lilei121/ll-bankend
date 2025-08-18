package com.ll.friendFrontendBanked.exception;

import com.ll.friendFrontendBanked.constant.BaseResponse;
import com.ll.friendFrontendBanked.constant.ErrorCode;
import com.ll.friendFrontendBanked.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(BaseException e){
        log.error(e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    public BaseResponse runtimeExceptionHandler(RuntimeException e){
        log.error("SYS_RunTimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }

}
