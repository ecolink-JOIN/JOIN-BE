package com.join.core.proof.service;

import com.join.core.proof.domain.Proof;

import java.util.List;
import java.util.Optional;

public interface ProofReader {

    boolean hasOngoingProof(Long avatarId, Long meetingId);
    Optional<Proof> findLastProof(Long avatarId, Long meetingId);
    List<Proof> findProofsByAvatarIdForJoinedStudies(Long avatarId);
    List<Proof> findProofsByAvatarIdForLeftStudies(Long avatarId);
}
