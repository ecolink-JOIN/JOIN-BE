package com.join.core.study.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

public record StudyScheduleDto(
        @Schema(description = "스터디 요일", example = "월")
        String weekOfDay,
        @Schema(description = "스터디 시작 시간", example = "14:00:00")
        LocalTime stTime,
        @Schema(description = "스터디 종료 시간", example = "14:00:00")
        LocalTime endTime
) {
}