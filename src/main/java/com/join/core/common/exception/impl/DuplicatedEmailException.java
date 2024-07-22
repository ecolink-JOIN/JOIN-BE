package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;
import com.join.core.user.constant.UserType;

import lombok.Getter;

@Getter
public class DuplicatedEmailException extends GeneralException {

	private final UserType requestedType;
	private final UserType existingType;

	public DuplicatedEmailException(ErrorCode errorCode, UserType requestedType, UserType existingType) {
		super(errorCode);
		this.requestedType = requestedType;
		this.existingType = existingType;
	}

}
