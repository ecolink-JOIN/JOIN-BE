package com.join.core.common.exception.impl;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.GeneralException;

public class FileUploadException extends GeneralException {

	public FileUploadException(ErrorCode errorCode) {
		super(errorCode);
	}

}
