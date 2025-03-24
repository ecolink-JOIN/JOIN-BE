package com.join.core.study.dto.response;

import com.join.core.schedule.dto.response.StudyScheduleResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.service.dto.FineReasonAmountsDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record StudyRuleResponse(
        @Schema(description = "스터디 시작 날짜", example = "2025-09-01")
        LocalDate startDate,
        @Schema(description = "스터디 종료 날짜", example = "2025-12-31")
        LocalDate endDate,
        @Schema(description = "스터디 스케쥴")
        List<StudyScheduleResponse> schedules,
        @Schema(description = "모임 방법", example = "OFFLINE")
        String form,
        @Schema(description = "스터디 규칙, 운영 규칙", example = "스터디 시작 시간 전후 10분(총 20분간) 출석 가능")
        String ruleExp,
        @Schema(description = "규칙 타입", example = "FINE, EXPULSION, PHOTO_PROOF, TIMER_PROOF")
        List<String> rules,
        @Schema(description = "벌금 규칙")
        FineReasonAmountsDto fineReasonAmounts
) {
    public static StudyRuleResponse of(Study study, List<StudyScheduleResponse> schedules, FineReasonAmountsDto fineReasonAmounts) {
        return new StudyRuleResponse(study.getStDate(), study.getEndDate(), schedules, study.getForm().name(), study.getRuleExp(), study.getRuleNames(), fineReasonAmounts);
    }
}
