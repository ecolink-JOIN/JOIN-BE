package com.join.core.auth.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.model.OAuth2Attributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OAuthSignInProcessorImpl implements OAuthSignInProcessor {

	private final UserReader userReader;
	private final UserStore userStore;
	private final OAuthSignInValidator oAuthSignInValidator;

	@Transactional
	@Override
	public UserInfo.SigIn signIn(OAuth2Attributes attributes) {
		UserInfo.SigIn signInInfo = signInOrUp(attributes);
		oAuthSignInValidator.validate(signInInfo, attributes);
		return signInInfo;
	}

	private UserInfo.SigIn signInOrUp(OAuth2Attributes attributes) {
		return signUpIfUnregistered(attributes, userReader.getSignInInfo(attributes.getEmail()));
	}

	private UserInfo.SigIn signUpIfUnregistered(OAuth2Attributes attributes, UserInfo.SigIn signInInfo) {
		if (signInInfo.isRegistered()) {
			return signInInfo;
		}
		return userStore.store(attributes);
	}

}
