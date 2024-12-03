package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class InvalidSelectionException extends GeneralException {

    public InvalidSelectionException(ErrorCode errorCode) {
        super(errorCode);
    }

}
