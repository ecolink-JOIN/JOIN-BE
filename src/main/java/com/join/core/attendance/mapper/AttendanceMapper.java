package com.join.core.attendance.mapper;

import com.join.core.attendance.domain.Attendance;
import com.join.core.avatar.domain.Avatar;
import com.join.core.meeting.domain.Meeting;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public Attendance toEntity(Meeting meeting, Avatar avatar) {
        return Attendance.builder()
                .meeting(meeting)
                .avatar(avatar)
                .build();
    }
}
