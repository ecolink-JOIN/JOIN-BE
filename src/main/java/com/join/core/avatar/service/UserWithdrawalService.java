package com.join.core.avatar.service;

import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;

public interface UserWithdrawalService {

	WithdrawalAvailabilityResponse canWithdrawal(Long avatarId);

}
