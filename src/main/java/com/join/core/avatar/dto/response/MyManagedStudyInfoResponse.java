package com.join.core.avatar.dto.response;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MyManagedStudyInfoResponse(
        @Schema(description = "스터디 토큰", example = "std_mfN0eg6IQI6k07ek")
        String studyToken,
        @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
        String name,
        @Schema(description = "모집 상태", example = "RECRUITING, READY, ACTIVE, COMPLETED 중 하나(모집중, 모집완료, 활동중, 활동완료)")
        StudyStatus status,
        @Schema(description = "평균 출결률", example = "56.25")
        double teamAverageAttendanceRate,
        @Schema(description = "평균 인증률", example = "56.25")
        double teamAverageProofRate,
        @Schema(description = "스터디원 별 평균 출결율, 인증률 및 모든 인증 승인 여부 리스트")
        List<StudyMemberAchievementDto> studyMembersInfos,
        @Schema(description = "스터디 카카오톡 링크", example = "https://open.kakao.com/o/joinjoinjoin")
        String kakaoUrl
) {
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

    public static MyManagedStudyInfoResponse of(Study study, double teamAverageAttendanceRate, double teamAverageProofRate, List<StudyMemberAchievementDto> achievementDtos) {
        return new MyManagedStudyInfoResponse(
                study.getStudyToken(),
                study.getStudyName(),
                study.getStatus(),
                teamAverageAttendanceRate,
                teamAverageProofRate,
                achievementDtos,
                study.getKakaoUrl()
        );
    }
}
