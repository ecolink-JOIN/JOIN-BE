package com.join.core.proof.dto.response;

import java.time.LocalDateTime;

public record CheckProofResponse(ProofStatusResponse proofStatusResponse, LocalDateTime provenTime) {
}
