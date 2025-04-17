package com.join.core.push.domain;

import com.join.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PushNotificationFailure extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	private String title;

	private String body;

	private String errorMessage;

	private Vendor vendor;

	public PushNotificationFailure(Long userId, SendFailureException exception) {
		this.userId = userId;
		this.title = exception.getTitle();
		this.body = exception.getBody();
		this.errorMessage = exception.getErrorMessage();
		this.vendor = exception.getVendor();
	}

}
