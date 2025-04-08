package com.join.core.avatar.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AvatarCommand {

	private AvatarCommand() {
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChangeNickname {
		@NotBlank
		private String nickname;

	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChangePhoto {
		private boolean defaultPhoto;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChangePushConsent {
		private boolean consent;
		private String fcmToken;
	}

}
