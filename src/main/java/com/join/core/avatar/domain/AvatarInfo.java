package com.join.core.avatar.domain;

import lombok.Builder;
import lombok.Getter;

public class AvatarInfo {

	private AvatarInfo() {
	}

	@Builder
	@Getter
	public static class ValidNickname {
		private final boolean isValid;
		private final String message;

		public static ValidNickname valid() {
			return new ValidNickname(true, "사용 가능한 닉네임이에요.");
		}
	}

}
