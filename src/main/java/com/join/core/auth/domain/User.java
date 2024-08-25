package com.join.core.auth.domain;

import static com.join.core.common.exception.ErrorCode.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.join.core.auth.constant.UserStatus;
import com.join.core.auth.constant.UserType;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.common.util.TokenGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

	private static final String USER_PREFIX = "usr_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userToken;

	@Column(unique = true)
	@NotNull
	private String email;

	private LocalDateTime singUpDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserType platform;

	@NotNull
	@OneToOne(mappedBy = "user")
	private Avatar avatar;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> userRoles;

	public Boolean isPending() {
		return UserStatus.PENDING.equals(status);
	}

	public boolean isUserOf(UserType providerType) {
		return providerType.equals(this.platform);
	}

	public User(String email, UserType platform) {
		if (StringUtils.isEmpty(email)) throw new InvalidParamException(INVALID_PARAMETER, "User.email");
		if (platform == null) throw new InvalidParamException(INVALID_PARAMETER, "User.platform");

		this.email = email;
		this.platform = platform;
		this.userToken = TokenGenerator.randomCharacterWithPrefix(USER_PREFIX);
		this.singUpDate = LocalDateTime.now();
		this.status = UserStatus.PENDING;
	}

	public void addRole(Role role) {
		userRoles.add(new UserRole(this, role));
	}

	public boolean isTermsAgreed() {
		return false;
	}

	public Set<SimpleGrantedAuthority> getAuthorities() {
		return Set.of();
	}

}
