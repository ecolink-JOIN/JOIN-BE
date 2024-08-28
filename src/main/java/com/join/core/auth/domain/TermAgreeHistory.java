package com.join.core.auth.domain;

import org.joda.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TermAgreeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@NotNull
	@JoinColumn(name = "term_id", referencedColumnName = "id")
	@JoinColumn(name = "term_version", referencedColumnName = "version")
	@ManyToOne(fetch = FetchType.LAZY)
	private Term term;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AcceptStatus status;

	@Getter
	@RequiredArgsConstructor
	public enum AcceptStatus {
		Y("동의"), N("거부");
		private final String description;
	}

	@PastOrPresent
	@NotNull
	private LocalDateTime agreeDate;

}
