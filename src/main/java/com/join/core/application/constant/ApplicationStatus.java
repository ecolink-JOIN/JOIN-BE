package com.join.core.application.constant;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    APPROVED("승인 완료"),
    REJECTED("거절 완료"),
    PENDING("승인 대기중");

    private final String statusName;

    ApplicationStatus(String statusName) {
        this.statusName = statusName;
    }

}
