package com.join.core.proof.service;

import com.join.core.proof.domain.Proof;

public interface ProofReader {

    boolean hasOngoingProof(Long avatarId, Long meetingId);
    Proof findLastProof(Long avatarId, Long meetingId);
    Proof getProofById(Long proofId);
}
