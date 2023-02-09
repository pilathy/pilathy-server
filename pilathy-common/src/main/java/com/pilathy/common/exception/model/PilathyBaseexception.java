package com.pilathy.common.exception.model;

import com.pilathy.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class PilathyBaseexception extends RuntimeException {

    private final ErrorCode errorCode;

    protected PilathyBaseexception(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected PilathyBaseexception(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public boolean isSetAlarm() {
        return errorCode.isSendNotification();
    }

    public int getStatus() {
        return errorCode.getStatus();
    }

}
