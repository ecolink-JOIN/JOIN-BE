package com.join.core.auth.domain;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.join.core.auth.constant.UserType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserInfo {

	private UserInfo() {
	}

	@Builder
	@Getter
	@ToString
	public static class SigIn {
		// User
		private final Long userId;
		private final String email;
		private final UserType platform;

		// Avatar
		private final Long avatarId;
		private final String avatarToken;
		private String nickname;

		// Role
		private final Set<SimpleGrantedAuthority> authorities;

		private final boolean registered;
		private final boolean newUser;
		private final boolean termsAgreed;
		private final boolean nicknameSet;

		public static SigIn unregistered() {
			return SigIn.builder()
				.registered(false)
				.build();
		}
	}

}
