package com.join.core.enrollment.constant;

import lombok.Getter;

@Getter
public enum EnrollmentStatus {
    PENDING(0.0),           // 대기 중: 0% 반영
    JOINED(1.0),            // 수락: 100% 반영
    REQUEST_LEAVE(1.0),     // 스터디장 동의에 의한 탈퇴: 100% 반영
    LEFT(0.5);              // 임의 탈퇴: 50% 반영

    private final double reflectionRate;

    EnrollmentStatus(double reflectionRate) {
        this.reflectionRate = reflectionRate;
    }

}
