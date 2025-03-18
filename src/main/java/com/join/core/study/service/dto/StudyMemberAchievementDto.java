package com.join.core.study.service.dto;

import com.join.core.avatar.domain.Avatar;
import io.swagger.v3.oas.annotations.media.Schema;

public record StudyMemberAchievementDto(
        @Schema(description = "스터디원 아바타 토큰", example = "token")
        String avatarToken,
        @Schema(description = "스터디원 별명", example = "닉네임")
        String nickname,
        @Schema(description = "이 스터디에서의 평균 출석률", example = "87.5")
        double averageAttendanceRate,
        @Schema(description = "이 스터디에서의 평균 인증률", example = "87.5")
        double averageProofRate,
        @Schema(description = "이 스터디의 모든 인증이 승인되었는지에 대한 여부", example = "true")
        boolean isFullyApproved
) {
    public static StudyMemberAchievementDto of(Avatar avatar, double averageAttendanceRate, double averageProofRate, boolean isFullyVerified) {
        return new StudyMemberAchievementDto(
                avatar.getAvatarToken(),
                avatar.getNickname(),
                averageAttendanceRate,
                averageProofRate,
                isFullyVerified
        );
    }
}