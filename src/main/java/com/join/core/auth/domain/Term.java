package com.join.core.auth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.join.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Term extends BaseTimeEntity {

	@EmbeddedId
	private Key id;

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Embeddable
	public static class Key implements Serializable {
		@NotNull
		private Long id;

		@NotNull
		@NotBlank
		private String version;
	}

	// 약관 종류
	@NotNull
	@Enumerated(EnumType.STRING)
	public Type type;

	@Getter
	@RequiredArgsConstructor
	public enum Type {
		REQUIRED("필수"), OPTIONAL("선택"), INACTIVE("종료");

		private final String description;
	}

	@NotNull
	@NotBlank
	private String title;

	@NotNull
	@NotBlank
	private String content;

	@NotNull
	private LocalDateTime startDate;

	@NotNull
	private LocalDateTime endDate;

}
