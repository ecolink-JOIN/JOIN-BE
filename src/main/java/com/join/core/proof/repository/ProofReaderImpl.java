package com.join.core.proof.repository;

import com.join.core.proof.service.ProofReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProofReaderImpl implements ProofReader {

    private static final boolean ONGOING_PROOF_STATUS = false;
    private final ProofRepository proofRepository;


    @Override
    public boolean hasOngoingProof(Long avatarId, Long meetingId) {
        return proofRepository.existsByAvatarIdAndMeetingIdAndProofStatus(avatarId, meetingId, ONGOING_PROOF_STATUS);
    }
}
