package com.join.core.auth.domain;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Type type;

	@Getter
	public enum Type {
		NO_PROFILE("NO_PROFILE"),
		USER("ROLE_USER"),
		ADMIN("ROLE_ADMIN"),
		GUEST("ROLE_GUEST");

		private final String authority;

		Type(String authority) {
			this.authority = authority;
		}

	}

	public Role(Type type) {
		this.type = type;
	}

	SimpleGrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority(this.type.getAuthority());
	}

}
