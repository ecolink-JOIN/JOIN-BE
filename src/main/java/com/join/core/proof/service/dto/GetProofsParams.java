package com.join.core.proof.service.dto;

public record GetProofsParams(
        String studyToken,
        String avatarToken,
        String targetToken
) {
}
