package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.service.AttendanceReader;
import com.join.core.enrollment.constant.EnrollmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AttendanceReaderImpl implements AttendanceReader {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceQueryRepository attendanceQueryRepository;

    @Override
    public boolean existsAttendance(Long avatarId, Long meetingId) {
        return attendanceRepository.existsAttendanceByAvatarIdAndMeetingId(avatarId, meetingId);
    }

    @Override
    public Optional<Attendance> findAttendance(Long avatarId, Long meetingId) {
        return attendanceRepository.findAttendanceByAvatarIdAndMeetingId(avatarId, meetingId);
    }

    @Override
    public List<Attendance> findAttendanceForJoinedStudy(Long avatarId) {
        return attendanceQueryRepository.findAttendancesByAvatarIdAndEnrollmentStatuses(avatarId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Attendance> findAttendanceForLeftStudy(Long avatarId) {
        return attendanceQueryRepository.findAttendancesByAvatarIdAndEnrollmentStatuses(avatarId, List.of(EnrollmentStatus.LEFT));
    }

    @Override
    public List<Attendance> findByStudyIdForJoinedStudy(Long studyId) {
        return attendanceQueryRepository.findAttendancesByStudyIdInEnrollmentStatuses(studyId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Attendance> findByStudyIdForLeftStudy(Long studyId) {
        return attendanceQueryRepository.findAttendancesByStudyIdInEnrollmentStatuses(studyId, List.of(EnrollmentStatus.LEFT));
    }

    @Override
    public List<Attendance> findByAvatarIdAndStudyIdForJoinedStudy(Long avatarId, Long studyId) {
        return attendanceQueryRepository.findAttendancesByAvatarIdAndStudyIdInEnrollmentStatuses(avatarId, studyId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Attendance> findByAvatarIdAndStudyIdForLeftStudy(Long avatarId, Long studyId) {
        return attendanceQueryRepository.findAttendancesByAvatarIdAndStudyIdInEnrollmentStatuses(avatarId, studyId, List.of(EnrollmentStatus.LEFT));
    }
}
