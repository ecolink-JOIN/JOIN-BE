package com.join.core.proof.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.proof.domain.Proof;

import java.util.List;

public interface ProofQueryRepository {
    List<Proof> findProofsByAvatarIdAndEnrollmentStatus(Long avatarId, List<EnrollmentStatus> statuses);
}
