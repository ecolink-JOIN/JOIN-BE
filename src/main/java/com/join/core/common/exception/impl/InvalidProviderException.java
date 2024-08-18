package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class InvalidProviderException extends GeneralException {
	public InvalidProviderException(ErrorCode errorCode) {
		super(errorCode);
	}
}
