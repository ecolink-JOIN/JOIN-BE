package com.join.core.attendance.service.command;

public record CheckAttendanceCommand(Long avatarId, String studyToken, int meetingNo) {
}
