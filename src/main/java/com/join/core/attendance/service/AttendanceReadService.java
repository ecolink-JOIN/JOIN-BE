package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.dto.response.CheckAttendanceResponse;
import com.join.core.attendance.service.command.CheckAttendanceCommand;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AttendanceReadService {

    private final AttendanceReader attendanceReader;
    private final MeetingReader meetingReader;
    private final StudyReader studyReader;
    private final AvatarReader avatarReader;
    private final EnrollmentReader enrollmentReader;
    private final ReturnTypeParser genericReturnTypeParser;

    public CheckAttendanceResponse checkAttendance(CheckAttendanceCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        checkMember(avatar.getId(), study.getId());

        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());

        return findAttendance(avatar.getId(), meeting.getId());
    }

    private void checkMember(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new NoPermissionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private CheckAttendanceResponse findAttendance(Long avatarId, Long meetingId) {
        Optional<Attendance> attendance = attendanceReader.findAttendance(avatarId, meetingId);
        return attendance
                .map(value -> new CheckAttendanceResponse(true, value.getCreatedDate()))
                .orElseGet(() -> new CheckAttendanceResponse(false, null));
    }
}
