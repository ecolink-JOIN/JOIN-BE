package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class BadRequestException extends GeneralException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
