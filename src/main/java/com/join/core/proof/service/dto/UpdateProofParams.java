package com.join.core.proof.service.dto;

import java.time.LocalDateTime;

public record UpdateProofParams(
        String avatarToken,
        String targetToken,
        String studyToken,
        Integer meetingNo,
        LocalDateTime provenTime
) {
}
