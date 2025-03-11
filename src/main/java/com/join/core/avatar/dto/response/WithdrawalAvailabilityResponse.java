package com.join.core.avatar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithdrawalAvailabilityResponse {

	private boolean canWithdraw;
	private String blockedReason;

	public static WithdrawalAvailabilityResponse available() {
		return new WithdrawalAvailabilityResponse(true, null);
	}

	public static WithdrawalAvailabilityResponse unavailable(String message) {
		return new WithdrawalAvailabilityResponse(false, message);
	}

}
