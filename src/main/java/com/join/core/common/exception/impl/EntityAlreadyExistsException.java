package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class EntityAlreadyExistsException extends GeneralException {

    public EntityAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }

}