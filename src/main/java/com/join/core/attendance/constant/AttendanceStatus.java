package com.join.core.attendance.constant;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    PRESENT(1.0),  // 출석: 100% 반영
    LATENESS(0.5), // 지각: 50% 반영
    ABSENT(0.0);   // 결석: 0% 반영

    private final double reflectionRate;

    AttendanceStatus(double reflectionRate) {
        this.reflectionRate = reflectionRate;
    }

}