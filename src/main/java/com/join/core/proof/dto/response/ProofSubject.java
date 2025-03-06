package com.join.core.proof.dto.response;

public record ProofSubject(
        String avatarToken,
        String nickname,
        String profileUrl,
        boolean isProofCompleted
) {
}
