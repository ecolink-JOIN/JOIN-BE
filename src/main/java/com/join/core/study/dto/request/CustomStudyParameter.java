package com.join.core.study.dto.request;

import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.TimeZone;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CustomStudyParameter(
        @Schema(example = "입시") String category,
        @Schema(example = "ONLINE") StudyForm form,
        List<DayType> possibleDays,
        @Schema(example = "MORNING") TimeZone timeZone,
        @Schema(example = "0") Integer minParticipationCount,
        @Schema(example = "7") Integer maxParticipationCount,
        @Schema(example = "서울특별시") String province,
        @Schema(example = "도봉구") String city
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
