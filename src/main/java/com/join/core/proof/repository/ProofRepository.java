package com.join.core.proof.repository;

import com.join.core.proof.constant.ProofStatus;
import com.join.core.proof.domain.Proof;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProofRepository extends JpaRepository<Proof, Long> {

    boolean existsByAvatarIdAndMeetingIdAndProofStatus(Long avatarId, Long meetingId, ProofStatus proofStatus);
    Optional<Proof> findFirstByAvatarIdAndMeetingIdOrderByIdDesc(Long avatarId, Long meetingId);
}
