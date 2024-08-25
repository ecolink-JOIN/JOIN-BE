package com.join.core.auth.domain;

import java.io.Serializable;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.join.core.auth.constant.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPrincipal implements Serializable {

	// User
	private final Long userId;
	private final String email;
	private final UserType platform;

	// Avatar
	private final Long avatarId;
	private final String avatarToken;
	private String nickname;

	// Role
	private final Set<? extends GrantedAuthority> authorities;

	public static UserPrincipal of(UserInfo.SigIn info) {
		return UserPrincipal.builder()
			.userId(info.getUserId())
			.email(info.getEmail())
			.platform(info.getPlatform())
			.avatarId(info.getAvatarId())
			.avatarToken(info.getAvatarToken())
			.nickname(info.getNickname())
			.authorities(info.getAuthorities())
			.build();
	}


}
