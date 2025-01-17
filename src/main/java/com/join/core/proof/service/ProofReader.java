package com.join.core.proof.service;

public interface ProofReader {

    boolean hasOngoingProof(Long avatarId, Long meetingId);
}
