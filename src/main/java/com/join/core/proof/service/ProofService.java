package com.join.core.proof.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.constant.ProofType;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.dto.response.CreateProofResponse;
import com.join.core.proof.mapper.ProofMapper;
import com.join.core.proof.service.dto.ApproveCommand;
import com.join.core.proof.service.dto.CreateProofCommand;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProofService {

    private final ProofStore proofStore;
    private final ProofReader proofReader;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final EnrollmentReader enrollmentReader;
    private final MeetingReader meetingReader;
    private final ProofPhotoReader proofPhotoReader;
    private final ProofMapper proofMapper;

    @Transactional
    public CreateProofResponse createProof(CreateProofCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        checkPermission(avatar.getId(), study.getId());
        checkProofType(command.proofType(), command.proofPhotoUrl());

        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());
        meeting.checkProofTime(command.provenDate());

        checkDuplicated(avatar.getId(), meeting.getId());
        ProofPhoto photo = proofPhotoReader.readPhoto(command.proofPhotoUrl());
        return proofMapper.toResponse(proofStore.save(proofMapper.toEntity(command, avatar, meeting, photo)));
    }

    private void checkPermission(Long avatarId, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(avatarId, studyId)) {
            throw new NoPermissionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }
    }

    private void checkProofType(ProofType proofType, String proofPhotoUrl) {
        if (proofType.isPhotoType() && StringUtils.isEmpty(proofPhotoUrl)) {
                throw new BadRequestException(ErrorCode.EMPTY_PROOF_PHOTO);
        }
    }

    private void checkDuplicated(Long avatarId, Long meetingId) {
        if (proofReader.hasOngoingProof(avatarId, meetingId)) {
            throw new BadRequestException(ErrorCode.DUPLICATED_PROOF);
        }
    }

    @Transactional
    public void approve(ApproveCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());
        Proof proof = proofReader.getProofById(command.proofId());
        proof.approve();
    }
}
