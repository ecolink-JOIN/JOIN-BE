package com.join.core.user.domain;

import java.time.LocalDateTime;

import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.user.constant.UserStatus;
import com.join.core.user.constant.UserType;

import jakarta.persistence.Column;
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
public class User extends BaseTimeEntity {

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

}
