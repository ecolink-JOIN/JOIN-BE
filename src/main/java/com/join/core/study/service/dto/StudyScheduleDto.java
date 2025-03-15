package com.join.core.study.service.dto;

import java.time.LocalTime;

public record StudyScheduleDto(
            String weekOfDay,
            LocalTime stTime,
            LocalTime endTime
    ) {
    }