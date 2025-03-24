package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsAttendanceByAvatarIdAndMeetingId(Long avatarId, Long meetingId);
    Optional<Attendance> findAttendanceByAvatarIdAndMeetingId(Long avatarId, Long meetingId);
    Optional<Attendance> findByMeetingIdAndAvatarId(Long meetingId, Long avatarId);
}
