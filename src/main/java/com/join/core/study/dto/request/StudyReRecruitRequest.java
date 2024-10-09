package com.join.core.study.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyReRecruitRequest {

    @Schema(description = "모집 인원", example = "10")
    @NotNull
    private int capacity;

    @Schema(description = "모집 종료 날짜", example = "2024-08-31")
    @NotNull
    private LocalDate recruitEndDate;

    @Schema(description = "글 제목", example = "직장인 영어 회화 스터디 구해요")
    @NotNull
    private String title;

    @Schema(description = "스터디 소개", example = "외국인 선생님과 함께하는 직장인 영어 회화 스터디 모집합니다.")
    @NotNull
    private String introduction;

    @Schema(description = "활동 내용", example = "5분씩 스피치 및 피드백")
    @NotNull
    private String content;

    @Schema(description = "지원 자격", example = "열정 있는 사람이라면 누구나")
    @NotNull
    private String qualificationExp;

}
