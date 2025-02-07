package com.join.core.avatar.dto.response;

import com.join.core.enrollment.constant.StudyRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MyInterestStudyResponse(
        @Schema(description = "관심 설정한 스터디 목록")
        List<InterestStudyInfoDto> interestStudyInfos
) {
    public record InterestStudyInfoDto(
            @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
            String studyName,
            @Schema(description = "스터디원 정보 리스트")
            List<StudyMemberInfoDto> studyMemberInfos,
            @Schema(description = "조회수", example = "101")
            Double viewCount
    ) {
        public static InterestStudyInfoDto of(String studyName, List<StudyMemberInfoDto> studyMemberInfos, Double viewCount) {
            return new InterestStudyInfoDto(studyName, studyMemberInfos, viewCount);
        }
    }
    public record StudyMemberInfoDto(
            @Schema(description = "스터디원 역할", example = "LEADER or MEMBER")
            String studyRole,
            @Schema(description = "스터디원 닉네임", example = "스터디장 이름")
            String nickname,
            @Schema(description = "스터디원 점수", example = "4.5")
            Double rating
    ) {

        public static StudyMemberInfoDto of(StudyRole studyRole, String nickname, Double rating) {
            return new StudyMemberInfoDto(studyRole.name(), nickname, rating);
        }
    }

    public static MyInterestStudyResponse of(List<InterestStudyInfoDto> joinStudyInfos) {
        return new MyInterestStudyResponse(joinStudyInfos);
    }
}
