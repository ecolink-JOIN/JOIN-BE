package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import com.join.core.enrollment.constant.EnrollmentStatus;

import java.util.List;

public interface AttendanceQueryRepository {
    List<Attendance> findAttendancesByAvatarIdAndEnrollmentStatuses(Long avatarId, List<EnrollmentStatus> statuses);
}
