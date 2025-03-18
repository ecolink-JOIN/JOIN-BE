package com.join.core.avatar.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;

public interface WithdrawalAvailabilityChecker {

	WithdrawalAvailabilityResponse check(Avatar avatar);

}
