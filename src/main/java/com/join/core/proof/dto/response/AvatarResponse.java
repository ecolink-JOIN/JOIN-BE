package com.join.core.proof.dto.response;

public record AvatarResponse(
        String avatarToken,
        String nickname,
        String profileUrl
) {
}
