package com.join.core.avatar.dto.response;

import com.join.core.avatar.domain.Avatar;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

public record MyInterestStudyResponse(
        @Schema(description = "관심 스터디 목록")
        List<InterestStudyInfoDto> myInterestStudies
) {
    public static MyInterestStudyResponse of(Map<List<Study>, List<Avatar>> interestStudyMap) {
        return new MyInterestStudyResponse(name, status);
    }

    public record InterestStudyInfoDto(
            @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
            String name,
            @Schema(description = "모집 상태", example = "RECRUITING, READY, ACTIVE, COMPLETED 중 하나(모집중, 모집완료, 활동중, 활동완료)")
            StudyStatus status,
            @Schema(description = "스터디 원 정보")
            List<StudyMemberInfoDto> memberInfos,
            @Schema(description = "조회수", example = "100")
            Long viewCount
    ) {
        public static MyJoinedStudyResponse.JoinStudyInfoDto of(String name, StudyStatus status) {
            return new MyJoinedStudyResponse.JoinStudyInfoDto(name, status);
        }
    }

    public record StudyMemberInfoDto(
            @Schema(description = "스터디원 닉네임", example = "스터디원 닉네임")
            String nickname,
            @Schema(description = "스터디 직책", example = "LEADER, MEMBER 중 하나")
            StudyRole role,
            @Schema(description = "평가 점수", example = "4.5")
            Long evaluationScore
    ) {
        public static MyJoinedStudyResponse.JoinStudyInfoDto of(String name, StudyRole role, StudyStatus status) {
            return new MyJoinedStudyResponse.JoinStudyInfoDto(name, role, status);
        }
    }
}
