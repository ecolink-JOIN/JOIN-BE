package com.join.core.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.dto.AuthenticationResult;
import com.join.core.auth.model.OAuth2Attributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthenticationService {

	private final OAuthSignInProcessor oauthSignInProcessor;

	@Transactional
	public AuthenticationResult oauthSignIn(OAuth2Attributes attributes) {
		var sigInInfo = oauthSignInProcessor.signIn(attributes);
		return new AuthenticationResult(sigInInfo);
	}

}
