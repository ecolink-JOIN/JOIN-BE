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
import com.join.core.proof.dto.response.ProofStatusResponse;
import com.join.core.proof.service.dto.CheckProofParams;
import com.join.core.proof.service.dto.ProofDetailParams;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProofReadService {

    private final ProofReader proofReader;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final EnrollmentReader enrollmentReader;
    private final MeetingReader meetingReader;

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
        checkPermission(avatar, params.studyToken());
        Proof proof = proofReader.getProofById(params.proofId());
        return mapToProofDetailResponse(proof);
    }

    public void checkPermission(Avatar avatar, String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        Avatar leader = enrollmentReader.getLeaderByStudyId(study.getId());
        if (!leader.isSameAvatar(avatar.getId())) {
            throw new LeaderForbiddenException(ErrorCode.NOT_LEADER_OF_STUDY);
        }
    }

    private ProofDetailResponse mapToProofDetailResponse(Proof proof) {
        if (proof.hasPhoto()) {
            return new ProofDetailResponse(
                    proof.getId(),
                    proof.getPhoto().getFile().getUrl(),
                    proof.getProvenDate()
            );
        }
        return new ProofDetailResponse(
                proof.getId(),
                null,
                proof.getProvenDate()
        );
    }
}
