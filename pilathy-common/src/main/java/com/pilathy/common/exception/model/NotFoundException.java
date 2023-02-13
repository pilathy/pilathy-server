package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class NotFoundException extends PilathyBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E404_NOT_EXISTS;

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
