package com.join.core.auth.domain;

import static com.join.core.common.exception.ErrorCode.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.join.core.auth.constant.UserType;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.ProfilePhoto;
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
	private Status status;

	@Getter
	public enum Status {
		PENDING, ACTIVE, INACTIVE, DELETED
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserType platform;

	@NotNull
	private boolean termsAgreed;

	@NotNull
	@OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
	private Avatar avatar;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> userRoles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<TermAgreeHistory> termAgreeHistoryList = new ArrayList<>();


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
		this.status = Status.PENDING;
		this.termsAgreed = false;
		this.avatar = new Avatar(this);
	}

	public void addRole(Role role) {
		userRoles.add(new UserRole(this, role));
	}

	public Set<SimpleGrantedAuthority> getAuthorities() {
		return this.userRoles.stream().map(UserRole::getAuthority).collect(Collectors.toUnmodifiableSet());
	}

	public void agree(Term term, TermAgreeHistory.AcceptStatus status) {
		if(ObjectUtils.isEmpty(term))
			throw new InvalidParamException(INVALID_PARAMETER, "agree.term");
		this.termAgreeHistoryList.add(new TermAgreeHistory(this, term, status));
	}

}
