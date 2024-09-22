package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class NoPermissionException extends GeneralException {

    public NoPermissionException(ErrorCode errorCode) {
        super(errorCode);
    }

}
