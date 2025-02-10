package com.join.core.avatar.dto.response;

import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MyInterestStudyResponse(
        @Schema(description = "관심 설정한 스터디 목록")
        List<InterestStudyInfoDto> interestStudyInfos
) {
    public record InterestStudyInfoDto(
            @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
            String studyName,
            @Schema(description = "모집 상태", example = "RECRUITING, READY, ACTIVE, COMPLETED 중 하나(모집중, 모집완료, 활동중, 활동완료)")
            StudyStatus status,
            @Schema(description = "스터디원 정보 리스트")
            List<StudyMemberInfoDto> studyMemberInfos,
            @Schema(description = "조회수", example = "101")
            int viewCount
    ) {
        public static InterestStudyInfoDto of(Study study, List<StudyMemberInfoDto> studyMemberInfos) {
            return new InterestStudyInfoDto(study.getStudyName(), study.getStatus(), studyMemberInfos, study.getViewCnt());
        }
    }
    public record StudyMemberInfoDto(
            @Schema(description = "스터디원 역할", example = "LEADER or MEMBER")
            String studyRole,
            @Schema(description = "스터디원 닉네임", example = "스터디장 이름")
            String nickname,
            @Schema(description = "스터디원 평가 점수", example = "4.5")
            Double rating
    ) {
        public static StudyMemberInfoDto of(StudyRole studyRole, String nickname, Double rating) {
            return new StudyMemberInfoDto(studyRole.name(), nickname, rating);
        }
    }

    public static MyInterestStudyResponse of(List<InterestStudyInfoDto> interestStudyInfos) {
        return new MyInterestStudyResponse(interestStudyInfos);
    }
}
