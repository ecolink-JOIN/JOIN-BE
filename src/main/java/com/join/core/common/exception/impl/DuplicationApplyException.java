package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class DuplicationApplyException extends GeneralException {
    public DuplicationApplyException(ErrorCode errorCode) {
        super(errorCode);
    }

}
