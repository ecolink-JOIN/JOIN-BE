package com.join.core.attendance.service;

import com.join.core.attendance.mapper.AttendanceMapper;
import com.join.core.attendance.service.dto.CreateAttendanceParams;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
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
        checkMeetingTime(meeting, params.now());
        attendanceSaver.save(attendanceMapper.toEntity(meeting, avatar));
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

    private void checkMeetingTime(Meeting meeting, LocalDateTime now) {
        if (!meeting.isWithinMeetingTime(now)) {
            throw new InvalidSelectionException(ErrorCode.OUT_OF_ATTENDANCE_TIME);
        }
    }
}
