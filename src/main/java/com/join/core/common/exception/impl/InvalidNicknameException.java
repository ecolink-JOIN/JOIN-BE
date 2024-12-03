package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

import lombok.Getter;

@Getter
public class InvalidNicknameException extends GeneralException {

	public InvalidNicknameException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
