package com.join.core.attendance.service.command;

import java.time.LocalDateTime;

public record CreateAttendanceCommand(Long avatarId, Integer meetingNo, LocalDateTime now) {
}
