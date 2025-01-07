package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class ServerStatusException extends GeneralException {

    public ServerStatusException(ErrorCode errorCode) {
        super(errorCode);
    }
}
