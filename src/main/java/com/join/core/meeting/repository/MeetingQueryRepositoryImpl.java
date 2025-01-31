package com.join.core.meeting.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.meeting.domain.Meeting;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.join.core.enrollment.domain.QEnrollment.*;
import static com.join.core.meeting.domain.QMeeting.*;
import static com.join.core.study.domain.QStudy.*;

@RequiredArgsConstructor
@Component
public class MeetingQueryRepositoryImpl implements MeetingQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Meeting> findMeetingsByAvatarIdAndEnrollmentStatuses(Long avatarId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(meeting)
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
