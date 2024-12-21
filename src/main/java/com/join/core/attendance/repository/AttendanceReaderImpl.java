package com.join.core.attendance.repository;

import com.join.core.attendance.service.AttendanceReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AttendanceReaderImpl implements AttendanceReader {

    private final AttendanceRepository attendanceRepository;

    @Override
    public boolean existsAttendance(Long avatarId, Long meetingId) {
        return attendanceRepository.existsAttendanceByAvatarIdAndMeetingId(avatarId, meetingId);
    }
}
