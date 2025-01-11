package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;

import java.util.Optional;

public interface AttendanceReader {

    boolean existsAttendance(Long avatarId, Long meetingId);

    Optional<Attendance> findAttendance(Long avatarId, Long meetingId);
}
