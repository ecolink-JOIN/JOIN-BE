package com.join.core.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.join.core.auth.domain.UserInfo;

import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthenticationResponse {

	private final Boolean newUser;
	private final boolean termsAgreed;
	private final boolean nicknameSet;

	public AuthenticationResponse(UserInfo.SigIn sigIn) {
		this.newUser = sigIn.isNewUser();
		this.termsAgreed = sigIn.isTermsAgreed();
		this.nicknameSet = sigIn.isNicknameSet();
	}

}
