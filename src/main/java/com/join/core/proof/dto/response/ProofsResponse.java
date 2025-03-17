package com.join.core.proof.dto.response;

import java.util.List;

public record ProofsResponse(
        String studyToken,
        AvatarResponse avatar,
        List<ProofResponse> proofs
) {
}
