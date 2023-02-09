package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

import static com.pilathy.common.exception.ErrorCode.E502_BAD_GATEWAY;

public class BadGatewayException extends PilathyBaseexception {

    private static final ErrorCode DEFAULT_ERROR_CODE = E502_BAD_GATEWAY;

    public BadGatewayException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

}
