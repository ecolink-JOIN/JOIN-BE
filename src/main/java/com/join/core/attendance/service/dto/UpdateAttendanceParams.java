package com.join.core.attendance.service.dto;

import com.join.core.attendance.constant.AttendanceStatus;

public record UpdateAttendanceParams(String avatarToken, AttendanceStatus status, Integer meetingNo, String targetAvatarToken, String studyToken) {
}