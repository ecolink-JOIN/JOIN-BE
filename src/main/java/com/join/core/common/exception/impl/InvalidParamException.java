package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class InvalidParamException extends GeneralException {

	public InvalidParamException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
