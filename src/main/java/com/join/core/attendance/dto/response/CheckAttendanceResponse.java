package com.join.core.attendance.dto.response;

import java.time.LocalDateTime;

public record CheckAttendanceResponse(boolean hasAttendance, LocalDateTime attendanceTime) {
}
