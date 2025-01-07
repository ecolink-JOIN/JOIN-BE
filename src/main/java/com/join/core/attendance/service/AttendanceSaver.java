package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;

public interface AttendanceSaver {

    void save(Attendance attendance);
}
