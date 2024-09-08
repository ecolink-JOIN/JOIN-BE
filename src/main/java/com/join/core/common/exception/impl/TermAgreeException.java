package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class TermAgreeException extends GeneralException {

	public TermAgreeException(ErrorCode errorCode) {
		super(errorCode);
	}

}
