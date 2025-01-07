package com.join.core.proof.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
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
    private final MeetingReader meetingReader;

    @Transactional
    public CheckProofResponse getProofStatus(CheckProofCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());
        Proof proof = proofReader.findLastProof(avatar.getId(), meeting.getId());
        return getProofResponse(proof);
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
