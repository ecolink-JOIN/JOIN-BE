package com.join.core.avatar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserWithdrawalServiceImpl implements UserWithdrawalService {

	private final AvatarReader avatarReader;
	private final List<WithdrawalAvailabilityChecker> withdrawalAvailabilityCheckers;

	@Override
	public WithdrawalAvailabilityResponse canWithdrawal(Long avatarId) {
		Avatar avatar = avatarReader.getAvatarById(avatarId);
		for (var checker : withdrawalAvailabilityCheckers) {
			var result = checker.check(avatar);
			if (!result.isCanWithdraw())
				return result;
		}
		return WithdrawalAvailabilityResponse.available();
	}

}
