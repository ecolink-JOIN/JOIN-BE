package com.join.core.proof.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.dto.response.CheckProofResponse;
import com.join.core.proof.dto.response.ProofStatusResponse;
import com.join.core.proof.service.dto.CheckProofCommand;
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

    @Transactional
    public CheckProofResponse getProofStatus(CheckProofCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        checkAuthorization(avatar.getId(), study.getId());

        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());
        Proof proof = proofReader.findLastProof(avatar.getId(), meeting.getId());

        return getProofResponse(proof);
    }

    private void checkAuthorization(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new BadRequestException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private CheckProofResponse getProofResponse(Proof proof) {
        if (proof == null) {
            return new CheckProofResponse(ProofStatusResponse.NONE, null);
        }
        return new CheckProofResponse(
                ProofStatusResponse.getProofStatusRequest(proof.getProofStatus()),
                proof.getProvenDate()
        );
    }
}
