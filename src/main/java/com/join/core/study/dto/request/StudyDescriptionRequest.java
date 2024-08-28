package com.join.core.study.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyDescriptionRequest {

    @Schema(description = "스터디명", example = "직장인 영어 회화 스터디 구해요")
    @NotNull
    private String studyName;

    @Schema(description = "스터디 소개", example = "외국인 선생님과 함께하는 직장인 영어 회화 스터디 모집합니다.")
    @NotNull
    private String introduction;

    @Schema(description = "활동 내용", example = "5분씩 스피치 및 피드백")
    @NotNull
    private String content;

    @Schema(description = "스터디 규칙", example = "지각 시 벌금 1,000원 부과")
    @NotNull
    private String ruleExp;

    @Schema(description = "지원 자격", example = "열정 있는 사람이라면 누구나")
    @NotNull
    private String qualificationExp;

}
