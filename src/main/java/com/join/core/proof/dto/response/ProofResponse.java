package com.join.core.proof.dto.response;

import java.time.LocalDateTime;

public record ProofResponse(
        Integer meetingNo,
        Long proofId,
        boolean isCompleted,
        LocalDateTime provenDate
) {
}
