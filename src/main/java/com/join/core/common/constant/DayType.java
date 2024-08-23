package com.join.core.common.constant;

import lombok.Getter;

@Getter
public enum DayType {
    SUN("일"),
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토");

    private final String name;

    DayType(String name) {
        this.name = name;
    }

}
