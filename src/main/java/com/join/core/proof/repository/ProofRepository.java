package com.join.core.proof.repository;

import com.join.core.proof.domain.Proof;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProofRepository extends JpaRepository<Proof, Long> {

    boolean existsByAvatarIdAndMeetingIdAndProofStatus(Long avatarId, Long meetingId, boolean proofStatus);
}
