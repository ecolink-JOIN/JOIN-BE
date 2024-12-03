package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class EntityNotFoundException extends GeneralException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}