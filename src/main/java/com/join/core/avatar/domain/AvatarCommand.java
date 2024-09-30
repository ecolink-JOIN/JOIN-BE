package com.join.core.avatar.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AvatarCommand {

	private AvatarCommand() {
	}

	@Getter
	public static class ChangeNickname {
		@NotBlank
		private String nickname;
	}

	@RequiredArgsConstructor
	@Getter
	public static class ChangePhoto {
		private final boolean defaultPhoto;
	}

}
