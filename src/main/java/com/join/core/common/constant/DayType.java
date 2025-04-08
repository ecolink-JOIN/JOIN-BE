package com.join.core.common.constant;

import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public enum DayType {
    SUN("일", DayOfWeek.SUNDAY),
    MON("월", DayOfWeek.MONDAY),
    TUE("화", DayOfWeek.TUESDAY),
    WED("수", DayOfWeek.WEDNESDAY),
    THU("목", DayOfWeek.THURSDAY),
    FRI("금", DayOfWeek.FRIDAY),
    SAT("토", DayOfWeek.SATURDAY);

    private final String name;
    private final DayOfWeek dayOfWeek;

    DayType(String name, DayOfWeek dayOfWeek) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
    }
}
