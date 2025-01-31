package com.join.core.attendance.service;

import com.join.core.attendance.domain.Attendance;
import com.join.core.common.util.NumberUtil;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.meeting.domain.MeetingReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttendanceRateService {

    private final AttendanceReader attendanceReader;
    private final MeetingReader meetingReader;

    @Transactional(readOnly = true)
    public double calculateIndividualAttendanceRate(Long avatarId) {
        List<Attendance> attendancesForJoinedStudies = attendanceReader.findAttendanceForJoinedStudy(avatarId);
        List<Attendance> attendancesForLeftStudies = attendanceReader.findAttendanceForLeftStudy(avatarId);

        long totalMeetings = meetingReader.findMeetingsByAvatarIdForStudies(avatarId).size();

        double totalAttendance = Stream.concat(
                        attendancesForJoinedStudies.stream(),
                        attendancesForLeftStudies.stream()
                )
                .mapToDouble(attendance -> {
                    double reflectionRate = attendance.getStatus().getReflectionRate();

                    if (attendancesForLeftStudies.contains(attendance)) {
                        reflectionRate *= EnrollmentStatus.LEFT.getReflectionRate();
                    }

                    return reflectionRate;
                })
                .sum();

        if (totalAttendance == 0) return 0.0;

        return NumberUtil.round(2, totalAttendance / totalMeetings * 100);
    }
    }
}
