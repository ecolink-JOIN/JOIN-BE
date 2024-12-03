package com.join.core.auth.service;

import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.model.OAuth2Attributes;

public interface OAuthSignInValidator {

	void validate(UserInfo.SigIn info, OAuth2Attributes attributes);

}
