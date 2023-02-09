package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class InvalidException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E400_INVALID;

    public InvalidException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public InvalidException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

    public InvalidException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
