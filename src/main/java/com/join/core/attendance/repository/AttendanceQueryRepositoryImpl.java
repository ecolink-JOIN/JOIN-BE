package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.domain.QAttendance;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.QEnrollment;
import com.join.core.meeting.domain.QMeeting;
import com.join.core.study.domain.QStudy;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AttendanceQueryRepositoryImpl implements AttendanceQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attendance> findAttendancesByAvatarIdAndEnrollmentStatuses(Long avatarId, List<EnrollmentStatus> statuses) {
        QAttendance attendance = QAttendance.attendance;
        QMeeting meeting = QMeeting.meeting;
        QStudy study = QStudy.study;
        QEnrollment enrollment = QEnrollment.enrollment;

        return queryFactory
                .selectFrom(attendance)
                .distinct()
                .join(attendance.meeting, meeting)
                .join(meeting.study, study)
                .where(
                        study.id.in(
                                JPAExpressions
                                        .select(enrollment.study.id)
                                        .from(enrollment)
                                        .where(
                                                enrollment.avatar.id.eq(avatarId)
                                                        .and(enrollment.status.in(statuses))
                                        )
                        )
                )
                .fetch();
    }
}
