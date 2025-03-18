package com.join.core.study.dto.request;


import com.join.core.schedule.dto.request.StudyScheduleRequest;
import com.join.core.study.constant.StudyForm;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record UpdateStudyRuleRequest(
        @Schema(description = "스터디 시작 날짜", example = "2025-09-01")
        LocalDate startDate,
        @Schema(description = "스터디 종료 날짜", example = "2025-12-31")
        LocalDate endDate,
        @Schema(description = "스터디 스케쥴")
        List<StudyScheduleRequest> schedules,
        @Schema(description = "모임 방법(ONLINE or OFFLINE)")
        FormDto form,
        @Schema(description = "스터디 규칙, 운영 규칙", example = "스터디 시작 시간 전후 10분(총 20분간) 출석 가능")
        String ruleExp,
        @Schema(description = "규칙 타입", example = "[\"FINE\", \"EXPULSION\", \"PHOTO_PROOF\", \"TIMER_PROOF\"]")
        List<String> rules,
        @Schema(description = "벌금 규칙")
        FineDto fine
) {
        public record FormDto(
                @Schema(description = "모임 방법(ONLINE or OFFLINE)", example = "OFFLINE")
                StudyForm form,
                @Schema(description = "시 - ONLINE이면 적용 안됨.", example = "서울특별시")
                String province,
                @Schema(description = "구 - ONLINE이면 적용 안됨.", example = "도봉구")
                String city
        ) {

        }
        public record FineDto(
                @Schema(description = "벌금 사용할지 안할지 Boolean - true면 위에 rule에서도 FINE을 넣어줘야합니다.", example = "true")
                Boolean isFineEnabled,
                @Schema(description = "지각에 대한 벌금 금액 - true면 반드시 필요", example = "1000")
                Integer tardiness,
                @Schema(description = "결석에 대한 벌금 금액 - true면 반드시 필요", example = "3000")
                Integer absence,
                @Schema(description = "미인증에 대한 벌금 금액 - true면 반드시 필요", example = "3000")
                Integer nonProof
        ) {

        }
}
