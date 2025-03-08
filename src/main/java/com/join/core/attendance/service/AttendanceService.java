package com.join.core.attendance.service;

import com.join.core.attendance.constant.AttendanceStatus;
import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.mapper.AttendanceMapper;
import com.join.core.attendance.service.dto.CreateAttendanceParams;
import com.join.core.attendance.service.dto.UpdateAttendanceParams;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.InvalidSelectionException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AvatarReader avatarReader;
    private final MeetingReader meetingReader;
    private final StudyReader studyReader;
    private final EnrollmentReader enrollmentReader;
    private final AttendanceReader attendanceReader;
    private final AttendanceSaver attendanceSaver;
    private final AttendanceMapper attendanceMapper;

    @Transactional
    public void createAttendance(CreateAttendanceParams params) {
        Avatar avatar = avatarReader.getAvatarById(params.avatarId());
        Study study = studyReader.getStudyByToken(params.studyToken());
        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), params.meetingNo());

        checkMember(avatar.getId(), study.getId());
        checkDuplicated(avatar.getId(), meeting.getId());
        AttendanceStatus attendanceStatus = checkMeetingTime(meeting, params.now());
        attendanceSaver.save(attendanceMapper.toEntity(attendanceStatus, meeting, avatar));
    }

    private void checkMember(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new InvalidSelectionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private void checkDuplicated(Long avatarId, Long meetingId) {
        if (attendanceReader.existsAttendance(avatarId, meetingId)) {
            throw new InvalidSelectionException(ErrorCode.ATTENDANCE_ALREADY_COMPLETED);
        }
    }

    private AttendanceStatus checkMeetingTime(Meeting meeting, LocalDateTime now) {
        if (!meeting.isAfterMeetingTime(now)) {
            throw new InvalidSelectionException(ErrorCode.OUT_OF_ATTENDANCE_TIME);
        }
        if (meeting.isLate(now)) {
            return AttendanceStatus.LATENESS;
        }
        return AttendanceStatus.PRESENT;
    }

    @Transactional
    public void updateAttendance(UpdateAttendanceParams params) {
        Study study = studyReader.getStudyByToken(params.studyToken());
        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), params.meetingNo());
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.avatarToken());
        if (enrollmentReader.getLeaderByStudyId(study.getId()).isSameAvatar(avatar.getId())) {
            throw new LeaderForbiddenException(ErrorCode.ATTENDANCE_UPDATE_FORBIDDEN);
        }
        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetAvatarToken());
        Attendance attendance = attendanceReader.findByMeetingIdAndAvatarId(meeting.getId(), target.getId());
        attendance.updateStatus(params.status());
    }
}
