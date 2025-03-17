package com.join.core.study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public enum TimeZone {
    MORNING(LocalTime.of(0, 0, 0), LocalTime.of(11, 59, 59, 59)),
    AFTERNOON(LocalTime.of(12, 0, 0), LocalTime.of(17, 59, 59, 59)),
    EVENING(LocalTime.of(18, 0, 0), LocalTime.of(23, 59, 59, 59)),;

    private final LocalTime startTime;
    private final LocalTime endTime;
}
