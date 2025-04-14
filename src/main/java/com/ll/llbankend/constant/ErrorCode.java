package com.ll.llbankend.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    PARAMS_ERROR(40000, "请求参数异常", ""),
    NULL_ERROR(40001, "响应异常", ""),
    UN_LOGIN(40101, "没有登录", ""),
    NO_AUTH(40100, "没有权限", ""),
    SYSTEM_ERROR(50000, "系统异常", "");




    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
