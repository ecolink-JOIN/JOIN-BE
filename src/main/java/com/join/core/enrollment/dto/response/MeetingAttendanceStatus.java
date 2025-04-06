package com.join.core.enrollment.dto.response;

import com.join.core.attendance.constant.AttendanceStatus;

import java.time.LocalDate;

public record MeetingAttendanceStatus(
        Integer meetingNo,
        LocalDate studyDate,
        AttendanceStatus attendanceStatus,
        boolean hasApproveProof
) {
}
