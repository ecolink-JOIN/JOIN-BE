package com.join.core.study.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.join.core.schedule.dto.request.StudyScheduleRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyRecruitRequest {

    @Schema(description = "모집 인원", example = "10")
    @NotNull
    private int capacity;

    @Schema(description = "정기 모임 여부", example = "true")
    @JsonProperty("regular")
    @NotNull
    private boolean isRegular;

    @Schema(description = "모집 종료 날짜", example = "2024-08-31")
    @NotNull
    private LocalDate recruitEndDate;

    @Schema(description = "시작 날짜", example = "2024-09-01")
    @NotNull
    private LocalDate stDate;

    @Schema(description = "종료 날짜", example = "2024-12-31")
    @NotNull
    private LocalDate endDate;

    @Schema(description = "시/도", example = "서울특별시")
    @NotNull
    private String province;

    @Schema(description = "시/군/구", example = "도봉구")
    @NotNull
    private String city;

    @Schema(description = "카테고리 이름", example = "입시")
    @NotNull
    private String categoryName;

    @Schema(description = "스터디명", example = "직장인 영어 회화 스터디")
    @NotNull
    private String studyName;

    @Schema(description = "글 제목", example = "직장인 영어 회화 스터디 구해요")
    @NotNull
    private String title;

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

    @Schema(description = "정기 모임 스케줄")
    private List<StudyScheduleRequest> schedules;

}
