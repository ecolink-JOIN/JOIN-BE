package com.join.core.study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public enum TimeZone {
    MORNING(LocalTime.of(0, 0, 0), LocalTime.of(12, 0, 0)),
    AFTERNOON(LocalTime.of(12, 0, 0), LocalTime.of(18, 0, 0)),
    EVENING(LocalTime.of(18, 0, 0), LocalTime.of(0, 0, 0)),;

    private final LocalTime startTime;
    private final LocalTime endTime;
}
