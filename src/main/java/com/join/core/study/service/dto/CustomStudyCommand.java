package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;

import java.util.List;

public record CustomStudyCommand(
        UserPrincipal userPrincipal,
        String category,
        StudyForm form,
        List<DayType> possibleDays,
        String timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        String province,
        String city
) {

}
