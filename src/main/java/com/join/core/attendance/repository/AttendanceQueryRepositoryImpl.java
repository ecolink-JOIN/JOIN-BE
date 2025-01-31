package com.join.core.attendance.repository;

import com.join.core.attendance.domain.Attendance;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.join.core.attendance.domain.QAttendance.*;
import static com.join.core.avatar.domain.QAvatar.*;
import static com.join.core.enrollment.domain.QEnrollment.*;
import static com.join.core.meeting.domain.QMeeting.*;
import static com.join.core.study.domain.QStudy.*;

@RequiredArgsConstructor
@Component
public class AttendanceQueryRepositoryImpl implements AttendanceQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attendance> findAttendancesByAvatarIdAndEnrollmentStatuses(Long avatarId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(attendance)
                .distinct()
                .join(attendance.meeting, meeting)
                .join(meeting.study, study)
                .where(
                        JPAExpressions
                                .selectOne()
                                .from(enrollment)
                                .where(
                                        enrollment.study.id.eq(study.id)
                                                .and(enrollment.avatar.id.eq(avatarId))
                                                .and(enrollment.status.in(statuses))
                                )
                                .exists(),
                        attendance.avatar.id.eq(avatarId)
                )
                .fetch();
    }

    @Override
    public List<Attendance> findAttendancesByStudyIdInEnrollmentStatuses(Long studyId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(attendance)
                .join(attendance.meeting, meeting)
                .join(attendance.avatar, avatar)
                .join(meeting.study, study).on(study.id.eq(studyId))
                .leftJoin(enrollment).on(
                        enrollment.study.eq(study)
                                .and(enrollment.avatar.eq(avatar))
                )
                .where(
                        enrollment.status.in(statuses)
                )
                .fetch();
    }

    @Override
    public List<Attendance> findAttendancesByAvatarIdAndStudyIdInEnrollmentStatuses(Long avatarId, Long studyId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(attendance)
                .join(attendance.meeting, meeting)
                .join(attendance.avatar, avatar).on(avatar.id.eq(avatarId))
                .join(meeting.study, study).on(study.id.eq(studyId))
                .leftJoin(enrollment).on(
                        enrollment.study.eq(study)
                                .and(enrollment.avatar.eq(avatar))
                )
                .where(
                        enrollment.status.in(statuses)
                )
                .fetch();
    }
}
