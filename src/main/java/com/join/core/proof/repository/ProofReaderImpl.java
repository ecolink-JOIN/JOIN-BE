package com.join.core.proof.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.proof.constant.ProofStatus;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.service.ProofReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProofReaderImpl implements ProofReader {

    private final ProofRepository proofRepository;

    @Override
    public boolean hasOngoingProof(Long avatarId, Long meetingId) {
        return proofRepository.existsByAvatarIdAndMeetingIdAndProofStatus(avatarId, meetingId, ProofStatus.PENDING) ||
                proofRepository.existsByAvatarIdAndMeetingIdAndProofStatus(avatarId, meetingId, ProofStatus.APPROVED);
    }

    @Override
    public Optional<Proof> findLastProof(Long avatarId, Long meetingId) {
        return proofRepository.findFirstByAvatarIdAndMeetingIdOrderByIdDesc(avatarId, meetingId);
    }

    @Override
    public Proof getProofById(Long proofId) {
        return proofRepository.findById(proofId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_PROOF_ID));
    }
}
