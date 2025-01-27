package com.join.core.proof.service.dto;

import com.join.core.proof.constant.ProofType;

import java.time.LocalDateTime;

public record CreateProofCommand(
        ProofType proofType,
        String proofPhotoUrl,
        Long avatarId,
        String studyToken,
        Integer meetingNo,
        LocalDateTime provenDate
) {
}
