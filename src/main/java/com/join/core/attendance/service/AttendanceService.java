package com.join.core.attendance.service;

import com.join.core.attendance.mapper.AttendanceMapper;
import com.join.core.attendance.service.command.CreateAttendanceCommand;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidSelectionException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.service.MeetingReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AvatarReader avatarReader;
    private final MeetingReader meetingReader;
    private final StudyReader studyReader;
    private final AttendanceSaver attendanceSaver;
    private final EnrollmentReader enrollmentReader;
    private final AttendanceMapper attendanceMapper;

    public void createAttendance(CreateAttendanceCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        Meeting meeting = meetingReader.findByMeetingNo(command.meetingNo());

        checkMember(avatar.getId(), study.getId());
        checkMeetingTime(meeting, command.now());
        attendanceSaver.save(attendanceMapper.toEntity(meeting, avatar));
    }

    private void checkMember(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new InvalidSelectionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private void checkMeetingTime(Meeting meeting, LocalDateTime now) {
        if (!meeting.isWithinMeetingTime(now)) {
            throw new InvalidSelectionException(ErrorCode.OUT_OF_ATTENDANCE_TIME);
        }
    }
}
