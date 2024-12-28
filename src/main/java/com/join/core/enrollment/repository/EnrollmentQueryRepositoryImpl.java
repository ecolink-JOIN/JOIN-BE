package com.join.core.enrollment.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.join.core.enrollment.domain.QEnrollment.enrollment;

@RequiredArgsConstructor
@Repository
public class EnrollmentQueryRepositoryImpl implements EnrollmentQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Double getMemberAverageByStudyId(Long studyId) {
        return queryFactory
                .select(enrollment.avatar.totalRating.avg().coalesce(0.0))
                .from(enrollment)
                .where(
                        enrollment.study.id.eq(studyId),
                        enrollment.role.eq(StudyRole.MEMBER),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .fetchOne();
    }

    @Override
    public Avatar getLeaderByStudyId(Long studyId) {
        return queryFactory
                .select(enrollment.avatar)
                .from(enrollment)
                .where(
                        enrollment.study.id.eq(studyId),
                        enrollment.role.eq(StudyRole.LEADER),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .fetchOne();
    }
}
