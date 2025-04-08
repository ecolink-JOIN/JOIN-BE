package com.join.core.attendance.service.dto;

import java.time.LocalDateTime;

public record CreateAttendanceParams(Long avatarId, String studyToken, Integer meetingNo, LocalDateTime now) {
}
