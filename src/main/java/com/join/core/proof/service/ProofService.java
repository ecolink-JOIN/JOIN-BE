package com.join.core.proof.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.dto.response.CreateProofResponse;
import com.join.core.proof.mapper.ProofMapper;
import com.join.core.proof.service.dto.CreateProofCommand;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProofService {

    private final ProofStore proofStore;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final MeetingReader meetingReader;
    private final ProofPhotoReader proofPhotoReader;
    private final ProofMapper proofMapper;

    @Transactional
    public CreateProofResponse createProof(CreateProofCommand command) {
        Avatar avatar = avatarReader.getAvatarById(command.avatarId());
        Study study = studyReader.getStudyByToken(command.studyToken());
        Meeting meeting = meetingReader.findByStudyIdAndMeetingNo(study.getId(), command.meetingNo());
        ProofPhoto photo = proofPhotoReader.readPhoto(command.proofPhotoUrl());
        Proof proof = proofStore.save(proofMapper.toEntity(command, avatar, meeting, photo));
        return proofMapper.toResponse(proof);
    }
}
