package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class InvalidStateException extends GeneralException {
    public InvalidStateException(ErrorCode errorCode) {
        super(errorCode);
    }

}
