package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;

public interface AttendanceReader {

    boolean existsAttendance(Long avatarId, Long meetingId);

    Attendance findAttendance(Long avatarId, Long meetingId);
}
