package com.join.core.avatar.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AvatarCommand {

	private AvatarCommand() {
	}

	@Getter
	@AllArgsConstructor
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
