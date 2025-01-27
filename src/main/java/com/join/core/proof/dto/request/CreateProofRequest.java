package com.join.core.proof.dto.request;

import com.join.core.proof.constant.ProofType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateProofRequest(
        @NotNull ProofType proofType,
        String proofPhotoUrl,
        @NotNull LocalDateTime provenDate) {
}
