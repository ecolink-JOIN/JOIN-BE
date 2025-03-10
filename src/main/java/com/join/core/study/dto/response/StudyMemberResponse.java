package com.join.core.study.dto.response;

import com.join.core.avatar.domain.Avatar;
import io.swagger.v3.oas.annotations.media.Schema;

public record StudyMemberResponse(
        @Schema(description = "아바타 토큰", example = "avt_fhEUF7C2C5tS0OC1")
        String avatarToken,
        @Schema(description = "스터디 역할", example = "LEADER")
        String role,
        @Schema(description = "스터디원 닉네임", example = "닉네임")
        String nickname
) {
    public static StudyMemberResponse of(Avatar avatar, String role) {
        return new StudyMemberResponse(avatar.getAvatarToken(), role, avatar.getNickname());
    }
}
