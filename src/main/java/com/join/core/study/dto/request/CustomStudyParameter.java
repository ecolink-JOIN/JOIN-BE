package com.join.core.study.dto.request;

import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.TimeZone;

import java.util.List;

public record CustomStudyParameter(
        String category,
        StudyForm form,
        List<DayType> possibleDays,
        TimeZone timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        String province,
        String city
) {

    public CustomStudyParameter {
        if (minParticipationCount == null) {
            minParticipationCount = 0;
        }
        if (maxParticipationCount == null) {
            maxParticipationCount = 7;
        }
    }
}
