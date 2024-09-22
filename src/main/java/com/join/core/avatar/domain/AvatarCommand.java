package com.join.core.avatar.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AvatarCommand {

	private AvatarCommand() {
	}

	@Getter
	public static class ChangeNickname {
		@NotBlank
		private String nickname;
	}

}
