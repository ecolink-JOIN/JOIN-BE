package com.join.core.auth.service;

import org.springframework.stereotype.Component;

import com.join.core.auth.constant.UserType;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.model.OAuth2Attributes;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.DuplicatedEmailException;

@Component
public class OAuthSignInPlatformValidator implements OAuthSignInValidator {

	@Override
	public void validate(UserInfo.SigIn info, OAuth2Attributes attributes) {
		if (info.isNewUser())
			return;
		UserType onDB = info.getPlatform();
		UserType requested = attributes.getPlatform();
		if (!onDB.equals(requested))
			throw new DuplicatedEmailException(ErrorCode.EMAIL_IS_REGISTER_WITH_ANOTHER_PROVIDER,
				requested, onDB);
	}

}
