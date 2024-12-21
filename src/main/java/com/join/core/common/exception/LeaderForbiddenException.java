package com.join.core.common.exception;

public class LeaderForbiddenException extends GeneralException {
    public LeaderForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
