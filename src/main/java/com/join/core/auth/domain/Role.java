package com.join.core.auth.domain;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.join.core.auth.constant.RoleType;

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
	private RoleType roleType;

	public Role(RoleType roleType) {
		this.roleType = roleType;
	}

	SimpleGrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority(this.roleType.getAuthority());
	}

}
