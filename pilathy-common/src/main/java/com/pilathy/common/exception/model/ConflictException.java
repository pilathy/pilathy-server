package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class ConflictException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_DUPLICATE;

    public ConflictException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

    public ConflictException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
