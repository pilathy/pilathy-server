package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class ForbiddenException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E403_FORBIDDEN;

    public ForbiddenException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

    public ForbiddenException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
