package com.join.core.avatar.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;
import com.join.core.study.service.StudyReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OngoingStudyChecker implements WithdrawalAvailabilityChecker {

	private final StudyReader studyReader;

	@Transactional(readOnly = true)
	@Override
	public WithdrawalAvailabilityResponse check(Avatar avatar) {

		for (var joinedStudy : studyReader.getJoinedStudiesByAvatarId(avatar.getId())) {
			if (joinedStudy.isActive())
				return WithdrawalAvailabilityResponse.unavailable("진행중인 스터디가 있습니다.");
		}

		return WithdrawalAvailabilityResponse.available();
	}

}
