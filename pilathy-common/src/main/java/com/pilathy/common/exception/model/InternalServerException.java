package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class InternalServerException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E500_INTERNAL_SERVER;

    public InternalServerException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

    public InternalServerException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
