package com.join.core.application.dto.response;

import com.join.core.application.domain.Application;
import com.join.core.avatar.domain.Avatar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationReadResponse {

    @Schema(description = "지원 ID", example = "1")
    private Long applicationId;

    @Schema(description = "스터디원 닉네임", example = "감자")
    private String nickname;

    @Schema(description = "지원 상태", example = "승인 대기중")
    private String applicationStatus;

    @Schema(description = "스터디 지원 이유", example = "퇴근 후 영어 공부 원해요")
    private String introduction;

    @Schema(description = "현재 참여 스터디 점수")
    private AvatarPerformance activeStudyStats;

    @Schema(description = "과거 참여 스터디 점수")
    private AvatarPerformance completedStudyStats;

    public static ApplicationReadResponse from(Application application, Avatar avatar,
                                               AvatarPerformance activeStudyStats, AvatarPerformance completedStudyStats) {
        return new ApplicationReadResponse(
                application.getId(),
                avatar.getNickname(),
                application.getStatus().getStatusName(),
                application.getIntroduction(),
                activeStudyStats,
                completedStudyStats
        );
    }
}
