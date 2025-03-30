package com.join.core.avatar.dto.response;

import com.join.core.avatar.domain.Avatar;

public record AvatarParticipationRateResponse(
        String avatarToken,
        String nickname,
        String profileUrl,
        double averageAttendanceRate,
        double averageProofRate

) {

    public static AvatarParticipationRateResponse of(Avatar avatar, Double averageAttendanceRate, Double averageProofRate) {
        return new AvatarParticipationRateResponse(
                avatar.getAvatarToken(),
                avatar.getNickname(),
                avatar.getPhoto().getFile().getUrl(),
                averageAttendanceRate,
                averageProofRate
        );
    }
}
