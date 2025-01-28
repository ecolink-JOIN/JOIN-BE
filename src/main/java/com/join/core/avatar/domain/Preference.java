package com.join.core.avatar.domain;

import java.util.List;

import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.TimeZone;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Builder
@DynamoDbBean
public class Preference {

	private String avatarToken;

	private String category;

	private StudyForm form;

	private List<DayType> possibleDays;

	private TimeZone timeZone;

	private Integer minParticipationCount;

	private Integer maxParticipationCount;

	private String province;

	private String city;

}
