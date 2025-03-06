package com.join.core.proof.service;

import com.join.core.proof.domain.Proof;

import java.util.List;
import java.util.Optional;

public interface ProofReader {

    boolean hasOngoingProof(Long avatarId, Long meetingId);
    Optional<Proof> findLastProof(Long avatarId, Long meetingId);
    List<Proof> findProofsByAvatarIdForJoinedStudies(Long avatarId);
    List<Proof> findProofsByAvatarIdForLeftStudies(Long avatarId);
    Proof getProofById(Long proofId);
    List<Proof> findByStudyIdForJoinedStudy(Long studyId);
    List<Proof> findByStudyIdForLeftStudy(Long studyId);
    List<Proof> findByAvatarIdAndStudyIdForJoinedStudy(Long avatarId, Long studyId);
    List<Proof> findByAvatarIdAndStudyIdForLeftStudy(Long avatarId, Long studyId);
    boolean isFullyApproved(Long avatarId, Long studyId);
    List<Proof> findByAvatarIdAndMeetingId(Long avatarId, Long meetingId);
    boolean existedPendingProofByAvatarIdAndMeetingId(Long avatarId, Long meetingId);
}
