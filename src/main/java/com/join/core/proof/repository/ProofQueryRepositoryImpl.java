package com.join.core.proof.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.proof.constant.ProofStatus;
import com.join.core.proof.domain.Proof;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.join.core.avatar.domain.QAvatar.*;
import static com.join.core.enrollment.domain.QEnrollment.*;
import static com.join.core.meeting.domain.QMeeting.*;
import static com.join.core.proof.domain.QProof.*;
import static com.join.core.study.domain.QStudy.*;

@RequiredArgsConstructor
@Component
public class ProofQueryRepositoryImpl implements ProofQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Proof> findProofsByAvatarIdAndEnrollmentStatus(Long avatarId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(proof)
                .distinct()
                .join(proof.meeting, meeting)
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
                        proof.avatar.id.eq(avatarId)
                )
                .fetch();
    }

    @Override
    public List<Proof> findProofsByStudyIdInEnrollmentStatuses(Long studyId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(proof)
                .join(proof.meeting, meeting)
                .join(proof.avatar, avatar)
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
    public List<Proof> findByAvatarIdAndStudyIdInEnrollmentStatuses(Long avatarId, Long studyId, List<EnrollmentStatus> statuses) {
        return queryFactory
                .selectFrom(proof)
                .join(proof.avatar, avatar).on(proof.avatar.id.eq(avatarId))
                .join(proof.meeting, meeting)
                .join(meeting.study, study).on(study.id.eq(studyId))
                .leftJoin(enrollment).on(
                        enrollment.study.eq(study)
                                .and(enrollment.avatar.eq(avatar)))
                .where(enrollment.status.in(statuses))
                .fetch();
    }

    @Override
    public boolean allProofsHaveApprovedStatus(Long avatarId, Long studyId) {
        Long countNonApproved = queryFactory
                .select(proof.count())
                .from(proof)
                .join(avatar).on(proof.avatar.id.eq(avatarId))
                .join(meeting).on(proof.meeting.id.eq(meeting.id))
                .join(study).on(study.id.eq(studyId))
                .where(
                        proof.status.ne(ProofStatus.APPROVED)
                )
                .fetchOne();

        return countNonApproved == null || countNonApproved == 0;
    }

    @Override
    public boolean existedPendingProofByAvatarIdAndStudyId(Long avatarId, Long studyId) {
        return queryFactory
                .select(proof)
                .from(proof)
                .join(avatar).on(proof.avatar.id.eq(avatarId))
                .join(meeting).on(proof.meeting.id.eq(meeting.id))
                .join(study).on(study.id.eq(studyId))
                .where(
                        proof.status.eq(ProofStatus.PENDING)
                )
                .fetchFirst() != null;
    }
}
