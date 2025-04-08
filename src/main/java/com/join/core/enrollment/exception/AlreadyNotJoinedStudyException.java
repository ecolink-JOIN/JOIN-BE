package com.join.core.enrollment.exception;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class AlreadyNotJoinedStudyException extends GeneralException {

    public AlreadyNotJoinedStudyException() {
        super(ErrorCode.ALREADY_NOT_JOINED);
    }
}
