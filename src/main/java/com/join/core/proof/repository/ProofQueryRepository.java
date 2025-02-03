package com.join.core.proof.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.proof.domain.Proof;

import java.util.List;

public interface ProofQueryRepository {
    List<Proof> findProofsByAvatarIdAndEnrollmentStatus(Long avatarId, List<EnrollmentStatus> statuses);
    List<Proof> findProofsByStudyIdInEnrollmentStatuses(Long studyId, List<EnrollmentStatus> statuses);
    List<Proof> findByAvatarIdAndStudyIdInEnrollmentStatuses(Long avatarId, Long studyId, List<EnrollmentStatus> statuses);
    boolean allProofsHaveApprovedStatus(Long avatarId, Long studyId);
}
