package com.join.core.avatar.dto;

import java.util.List;

import com.join.core.avatar.domain.Preference;
import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePreferenceRequest {

	private String category;

	private StudyForm form;

	private List<DayType> possibleDays;

	private TimeZone timeZone;

	private Integer minParticipationCount;

	private Integer maxParticipationCount;

	private String province;

	private String city;

	public Preference toObject(String avatarToken) {
		return Preference.builder()
			.avatarToken(avatarToken)
			.category(category)
			.form(form)
			.possibleDays(possibleDays)
			.timeZone(timeZone)
			.minParticipationCount(minParticipationCount)
			.maxParticipationCount(maxParticipationCount)
			.province(province)
			.city(city)
			.build();
	}

}
