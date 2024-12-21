package com.join.core.attendance.service;

import com.join.core.attendance.mapper.AttendanceMapper;
import com.join.core.attendance.service.command.CreateAttendanceCommand;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.service.MeetingReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        attendanceSaver.save(attendanceMapper.toEntity(meeting, avatar));
    }
}
