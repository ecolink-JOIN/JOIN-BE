package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceReader {

    boolean existsAttendance(Long avatarId, Long meetingId);

    Optional<Attendance> findAttendance(Long avatarId, Long meetingId);

    List<Attendance> findAttendanceForJoinedStudy(Long avatarId);

    List<Attendance> findAttendanceForLeftStudy(Long avatarId);

    List<Attendance> findByStudyIdForJoinedStudy(Long studyId);

    List<Attendance> findByStudyIdForLeftStudy(Long studyId);

    List<Attendance> findByAvatarIdAndStudyIdForJoinedStudy(Long avatarId, Long studyId);

    List<Attendance> findByAvatarIdAndStudyIdForLeftStudy(Long avatarId, Long studyId);
}
