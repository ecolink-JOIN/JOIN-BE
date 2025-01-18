package com.join.core.proof.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.QEnrollment;
import com.join.core.meeting.domain.QMeeting;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.domain.QProof;
import com.join.core.study.domain.QStudy;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProofQueryRepositoryImpl implements ProofQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Proof> findProofsByAvatarIdAndEnrollmentStatus(Long avatarId, List<EnrollmentStatus> statuses) {
        QProof proof = QProof.proof;
        QMeeting meeting = QMeeting.meeting;
        QStudy study = QStudy.study;
        QEnrollment enrollment = QEnrollment.enrollment;

        return queryFactory
                .selectFrom(proof)
                .distinct()
                .join(proof.meeting, meeting)
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
