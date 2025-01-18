package com.join.core.proof.repository;

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
        return proofRepository.existsByAvatarIdAndMeetingIdAndStatus(avatarId, meetingId, ProofStatus.PENDING) ||
                proofRepository.existsByAvatarIdAndMeetingIdAndStatus(avatarId, meetingId, ProofStatus.APPROVED);
    }

    @Override
    public Optional<Proof> findLastProof(Long avatarId, Long meetingId) {
        return proofRepository.findFirstByAvatarIdAndMeetingIdOrderByIdDesc(avatarId, meetingId);
    }
}
