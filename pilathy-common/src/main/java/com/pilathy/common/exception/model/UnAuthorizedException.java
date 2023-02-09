package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class UnAuthorizedException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E401_UNAUTHORIZED;

    public UnAuthorizedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

}
