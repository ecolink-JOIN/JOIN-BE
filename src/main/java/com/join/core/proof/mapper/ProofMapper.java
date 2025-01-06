package com.join.core.proof.mapper;

import com.join.core.avatar.domain.Avatar;
import com.join.core.meeting.domain.Meeting;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.dto.response.CreateProofResponse;
import com.join.core.proof.service.dto.CreateProofCommand;
import org.springframework.stereotype.Component;

@Component
public class ProofMapper {

    public Proof toEntity(CreateProofCommand command, Avatar avatar, Meeting meeting, ProofPhoto photo) {
        return Proof.builder()
                .type(command.proofType())
                .provenDate(command.provenDate())
                .avatar(avatar)
                .meeting(meeting)
                .photo(photo)
                .proofStatus(false)
                .build();
    }

    public CreateProofResponse toResponse(Proof proof) {
        return CreateProofResponse.builder()
                .id(proof.getId())
                .proofType(proof.getType())
                .proofPhotoUrl(proof.getPhoto().getFile().getUrl())
                .provenDate(proof.getProvenDate())
                .build();
    }
}
