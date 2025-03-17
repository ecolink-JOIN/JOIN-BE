package com.join.core.proof.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.dto.response.CheckProofResponse;
import com.join.core.proof.dto.response.ProofDetailResponse;
import com.join.core.proof.dto.response.ProofResponse;
import com.join.core.proof.dto.response.ProofStatusResponse;
import com.join.core.proof.dto.response.ProofsResponse;
import com.join.core.proof.mapper.ProofMapper;
import com.join.core.proof.service.dto.CheckProofParams;
import com.join.core.proof.service.dto.GetProofsParams;
import com.join.core.proof.service.dto.ProofDetailParams;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProofReadService {

    private final ProofReader proofReader;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final EnrollmentReader enrollmentReader;
    private final MeetingReader meetingReader;
    private final ProofMapper proofMapper;

    @Transactional(readOnly = true)
    public CheckProofResponse getProofStatus(CheckProofParams params) {
        Avatar avatar = avatarReader.getAvatarById(params.avatarId());
        Study study = studyReader.getStudyByToken(params.studyToken());
        checkAuthorization(avatar.getId(), study.getId());

        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), params.meetingNo());

        return findProof(avatar.getId(), meeting.getId());
    }

    private void checkAuthorization(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new BadRequestException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private CheckProofResponse findProof(Long avatarId, Long meetingId) {
        return proofReader.findLastProof(avatarId, meetingId)
                .map(proof -> new CheckProofResponse(
                        ProofStatusResponse.getProofStatusRequest(proof.getStatus()),
                        proof.getProvenDate()
                ))
                .orElse(new CheckProofResponse(ProofStatusResponse.NONE, null));
    }

    @Transactional(readOnly = true)
    public ProofDetailResponse getProofDetail(ProofDetailParams params) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.avatarToken());
        checkLeaderPermission(avatar, params.studyToken());
        Proof proof = proofReader.getProofById(params.proofId());
        return new ProofDetailResponse(
                proof.getId(),
                proof.getPhotoUrl(),
                proof.getProvenDate()
        );
    }

    public void checkLeaderPermission(Avatar avatar, String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        Avatar leader = enrollmentReader.getLeaderByStudyId(study.getId());
        if (!leader.isSameAvatar(avatar.getId())) {
            throw new LeaderForbiddenException(ErrorCode.LEADER_ONLY_ACCESS);
        }
    }

    @Transactional(readOnly = true)
    public ProofsResponse getProofs(GetProofsParams params) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.avatarToken());
        Study study = studyReader.getStudyByToken(params.studyToken());
        checkLeaderPermission(avatar, study.getStudyToken());

        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetToken());
        List<Proof> proofs = findProofs(study.getId(), target);

        return mapToResponse(study.getStudyToken(), target, proofs);
    }

    private List<Proof> findProofs(Long studyId, Avatar target) {
        List<Meeting> meetings = meetingReader.findMeetingsByStudyId(studyId);
        List<Proof> proofs = new ArrayList<>();
        for (Meeting meeting : meetings) {
            proofs.addAll(proofReader.findByAvatarIdAndMeetingId(target.getId(), meeting.getId()));
        }
        return  proofs;
    }

    private ProofsResponse mapToResponse(String studyToken, Avatar target, List<Proof> proofs) {
        List<ProofResponse> proofResponse = proofs.stream()
                .map(proofMapper::toProofResponse)
                .toList();
        return proofMapper.toProofsResponse(studyToken, target, proofResponse);
    }
}
