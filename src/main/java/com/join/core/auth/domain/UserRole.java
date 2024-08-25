package com.join.core.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@NotNull
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

}
