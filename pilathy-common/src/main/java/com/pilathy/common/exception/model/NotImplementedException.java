package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class NotImplementedException extends PilathyBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E501_NOT_SUPPORTED;

    public NotImplementedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotImplementedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

    public NotImplementedException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
