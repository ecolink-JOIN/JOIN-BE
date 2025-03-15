package com.join.core.proof.dto.response;

import java.time.LocalDateTime;

public record ProofDetailResponse(
        Long proofId,
        String proofPhotoUrl,
        LocalDateTime provenTime
) {
}
