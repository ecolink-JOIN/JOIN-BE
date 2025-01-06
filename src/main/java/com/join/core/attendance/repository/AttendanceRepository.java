package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsAttendanceByAvatarIdAndMeetingId(Long avatarId, Long meetingId);
}
