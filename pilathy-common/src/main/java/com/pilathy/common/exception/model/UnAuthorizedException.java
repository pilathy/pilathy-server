package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class UnAuthorizedException extends PilathyBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E401_UNAUTHORIZED;

    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnAuthorizedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

}
