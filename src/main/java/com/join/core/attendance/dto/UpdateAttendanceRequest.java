package com.join.core.attendance.dto;

import com.join.core.attendance.constant.AttendanceStatus;

public record UpdateAttendanceRequest(AttendanceStatus status, String targetAvatarToken) {
}
