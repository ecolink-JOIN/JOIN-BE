package com.join.core.proof.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.proof.constant.ProofStatus;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.service.ProofReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProofReaderImpl implements ProofReader {

    private final ProofRepository proofRepository;
    private final ProofQueryRepository proofQueryRepository;

    @Override
    public boolean hasOngoingProof(Long avatarId, Long meetingId) {
        return proofRepository.existsByAvatarIdAndMeetingIdAndStatus(avatarId, meetingId, ProofStatus.PENDING) ||
                proofRepository.existsByAvatarIdAndMeetingIdAndStatus(avatarId, meetingId, ProofStatus.APPROVED);
    }

    @Override
    public Optional<Proof> findLastProof(Long avatarId, Long meetingId) {
        return proofRepository.findFirstByAvatarIdAndMeetingIdOrderByIdDesc(avatarId, meetingId);
    }

    @Override
    public List<Proof> findProofsByAvatarIdForJoinedStudies(Long avatarId) {
        return proofQueryRepository.findProofsByAvatarIdAndEnrollmentStatus(avatarId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Proof> findProofsByAvatarIdForLeftStudies(Long avatarId) {
        return proofQueryRepository.findProofsByAvatarIdAndEnrollmentStatus(avatarId, List.of(EnrollmentStatus.LEFT));
    }

    @Override
    public Proof getProofById(Long proofId) {
        return proofRepository.findById(proofId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_PROOF_ID));
    }

    @Override
    public List<Proof> findByStudyIdForJoinedStudy(Long studyId) {
        return proofQueryRepository.findProofsByStudyIdInEnrollmentStatuses(studyId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Proof> findByStudyIdForLeftStudy(Long studyId) {
        return proofQueryRepository.findProofsByStudyIdInEnrollmentStatuses(studyId, List.of(EnrollmentStatus.LEFT));
    }

    @Override
    public List<Proof> findByAvatarIdAndStudyIdForJoinedStudy(Long avatarId, Long studyId) {
        return proofQueryRepository.findByAvatarIdAndStudyIdInEnrollmentStatuses(avatarId, studyId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
    }

    @Override
    public List<Proof> findByAvatarIdAndStudyIdForLeftStudy(Long avatarId, Long studyId) {
        return proofQueryRepository.findByAvatarIdAndStudyIdInEnrollmentStatuses(avatarId, studyId, List.of(EnrollmentStatus.LEFT));
    }

    @Override
    public boolean isFullyApproved(Long avatarId, Long studyId) {
        return proofQueryRepository.allProofsHaveApprovedStatus(avatarId, studyId);
    }

    @Override
    public List<Proof> findByAvatarIdAndMeetingId(Long avatarId, Long meetingId) {
        return proofRepository.findAllByAvatarIdAndMeetingId(avatarId, meetingId);
    }

    @Override
    public boolean existedPendingProofByAvatarIdAndMeetingId(Long avatarId, Long meetingId) {
        return proofRepository.existsByAvatarIdAndMeetingIdAndStatus(avatarId, meetingId, ProofStatus.PENDING);
    }
}
