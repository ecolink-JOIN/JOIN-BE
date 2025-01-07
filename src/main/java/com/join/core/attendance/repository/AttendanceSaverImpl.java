package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.service.AttendanceSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceSaverImpl implements AttendanceSaver {

    private final AttendanceRepository attendanceRepository;

    @Override
    public void save(Attendance attendance) {
        attendanceRepository.save(attendance);
    }
}
