package com.ll.friendFrontendBanked.exception;

import com.ll.friendFrontendBanked.constant.ErrorCode;
import lombok.Getter;


/**
 * 自定义异常类
 */
@Getter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int code;

    private final String description;

    public BaseException(int code, String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public BaseException(ErrorCode errorCode, String message, String description) {
        super(message);
        this.code = errorCode.getCode();
        this.description = description;
    }
}
