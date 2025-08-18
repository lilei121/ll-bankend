package com.ll.friendFrontendBanked.utils;

import com.ll.friendFrontendBanked.constant.BaseResponse;
import com.ll.friendFrontendBanked.constant.ErrorCode;

/**
 *
 */
public class ResultUtils {
    // 成功响应
    public static <T>BaseResponse success(T data){
        return new BaseResponse(0,data,"success");
    }

    // 失败响应
    public static <T>BaseResponse error(ErrorCode errorCode){
        return new BaseResponse(errorCode);
    }

    public static <T>BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse(errorCode, message, description);
    }

    public static <T>BaseResponse error(int errorCode,String message,String description){
        return new BaseResponse(errorCode, message, description);
    }
}
