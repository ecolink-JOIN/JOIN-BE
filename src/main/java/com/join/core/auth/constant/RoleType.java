package com.join.core.auth.constant;

import lombok.Getter;

@Getter
public enum RoleType {

	NO_PROFILE("NO_PROFILE"),
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	GUEST("ROLE_GUEST");

	private final String authority;

	RoleType(String authority) {
		this.authority = authority;
	}

}
