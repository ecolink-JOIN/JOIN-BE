package com.join.core.proof.dto.response;

import com.join.core.proof.constant.ProofType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateProofResponse(
        Long id,
        ProofType proofType,
        String proofPhotoUrl,
        LocalDateTime provenDate
) {
}
