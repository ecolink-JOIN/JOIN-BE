package com.join.core.avatar.dto.response;

import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import com.join.core.study.service.dto.StudyMemberAchievementDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MyManagedStudyInfoResponse(
        @Schema(description = "스터디 토큰", example = "std_mfN0eg6IQI6k07ek")
        String studyToken,
        @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
        String name,
        @Schema(description = "모집 상태", example = "RECRUITING, READY, ACTIVE_RECRUITING, ACTIVE, COMPLETED 중 하나(시작 전 모집 중, 시작 전 준비 완료, 진행중 + 모집O, 진행중 + 모집X, 활동 종료)")
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
