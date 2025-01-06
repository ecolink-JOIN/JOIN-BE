package com.join.core.attendance.service;

public interface AttendanceReader {

    boolean existsAttendance(Long avatarId, Long meetingId);
}
