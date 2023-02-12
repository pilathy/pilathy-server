package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;

public class ServiceUnAvailableException extends PilathyBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E503_SERVICE_UNAVAILABLE;

    public ServiceUnAvailableException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public ServiceUnAvailableException(String message, Throwable cause) {
        super(message, DEFAULT_ERROR_CODE, cause);
    }

}
