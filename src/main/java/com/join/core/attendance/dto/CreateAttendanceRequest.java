package com.join.core.attendance.dto;

import java.time.LocalDateTime;

public record CreateAttendanceRequest(LocalDateTime now) {
}
