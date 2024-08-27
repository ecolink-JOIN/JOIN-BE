package com.join.core.auth.dto;

import com.join.core.auth.domain.AuthenticationToken;
import com.join.core.auth.domain.UserInfo;

import lombok.Getter;

@Getter
public class AuthenticationResult {

	private final AuthenticationToken token;
	private final AuthenticationResponse response;

	public AuthenticationResult(AuthenticationToken token, AuthenticationResponse response) {
		this.token = token;
		this.response = response;
	}

	public AuthenticationResult(UserInfo.SigIn sigIn) {
		this.token = new AuthenticationToken(sigIn);
		this.response = new AuthenticationResponse(sigIn);
	}
}
