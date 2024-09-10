package com.join.core.avatar.domain;

import lombok.Builder;
import lombok.Getter;

public class AvatarCommand {

	private AvatarCommand() {
	}

	@Builder
	@Getter
	public static class ChangeNickname {
		private final String nickname;
	}

}
