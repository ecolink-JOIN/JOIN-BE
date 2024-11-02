package com.join.core.avatar.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.join.core.auth.constant.UserType;
import com.join.core.auth.domain.User;
import com.join.core.common.exception.ErrorCode;
import com.join.core.file.domain.ImageFile;

import lombok.Builder;
import lombok.Getter;

public class AvatarInfo {

	private AvatarInfo() {
	}

	@Builder
	@Getter
	public static class Self {
		private final String avatarToken;
		private final String nickname;
		private final int totalRating;
		private final int ratingCnt;

		//User
		private final String email;
		private final LocalDateTime singUpDate;
		private final User.Status status;
		private final UserType platform;
		// TODO 약관 동의 여부 추가

		// ProfilePhoto
		private final ImageFile image;

	}

	@Builder
	@Getter
	public static class ValidNickname {
		private final boolean isValid;
		private final String message;
		@JsonIgnore
		private final ErrorCode errorCode;

		public static ValidNickname valid() {
			return new ValidNickname(true, "사용 가능한 닉네임이에요.", null);
		}
	}

}
