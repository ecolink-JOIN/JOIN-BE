package com.join.core.proof.dto.request;

import java.time.LocalDateTime;

public record UpdateProofRequest(
        String targetToken,
        LocalDateTime provenTime
) {
}
