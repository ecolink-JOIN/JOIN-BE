package com.join.core.auth.model;

import java.util.Map;

import com.join.core.auth.constant.UserType;
import com.join.core.auth.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2Attributes {

	private Map<String, Object> attributes;
	private UserType platform;
	private String attributeKey;
	private String email;
	private String name;
	private boolean hasImage;
	private String profileImage;

	public static OAuth2Attributes of(Map<String, Object> attributes) {
		return OAuth2Attributes.builder()
			.platform((UserType)attributes.get("platform"))
			.attributeKey((String)attributes.get("key"))
			.email((String)attributes.get("email"))
			.name((String)attributes.get("name"))
			.hasImage((Boolean)attributes.get("hasImage"))
			.profileImage((String)attributes.get("profileImage"))
			.build();
	}

	public Map<String, Object> toMap() {
		return Map.of(
			"platform", platform,
			"key", attributeKey,
			"email", email,
			"name", name,
			"hasImage", hasImage,
			"profileImage", profileImage);
	}

	public User toEntity() {
		return new User(email, platform);
	}

	public boolean hasImage() {
		return this.hasImage;
	}

}
