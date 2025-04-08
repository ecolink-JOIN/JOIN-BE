package com.join.core.enrollment.exception;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class UnapprovedRemainingException extends GeneralException {

    public UnapprovedRemainingException() {
        super(ErrorCode.UNAPPROVED_REMAINING);
    }
}
