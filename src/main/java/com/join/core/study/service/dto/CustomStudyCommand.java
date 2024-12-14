package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;

import java.time.LocalDateTime;
import java.util.List;

public record CustomStudyCommand(
        UserPrincipal userPrincipal,
        String category,
        StudyForm form,
        List<DayType> possibleDays,
        String timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        LocalDateTime now,
        String province,
        String city
) {

}
