package com.join.core.attendance.service;

import com.join.core.attendance.mapper.AttendanceMapper;
import com.join.core.attendance.service.command.CreateAttendanceCommand;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidSelectionException;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.service.MeetingReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AvatarReader avatarReader;
    private final MeetingReader meetingReader;
    private final AttendanceSaver attendanceSaver;
    private final AttendanceMapper attendanceMapper;

    public void createAttendance(CreateAttendanceCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Meeting meeting = meetingReader.findByMeetingNo(command.meetingNo());
        checkMeetingTime(meeting, command.now());
        attendanceSaver.save(attendanceMapper.toEntity(meeting, avatar));
    }

    private void checkMeetingTime(Meeting meeting, LocalDateTime now) {
        if (!meeting.isWithinMeetingTime(now)) {
            throw new InvalidSelectionException(ErrorCode.OUT_OF_ATTENDANCE_TIME);
        }
    }
}
