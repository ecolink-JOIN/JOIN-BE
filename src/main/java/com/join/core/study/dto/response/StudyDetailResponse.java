package com.join.core.study.dto.response;

import com.join.core.schedule.dto.response.StudyScheduleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class StudyDetailResponse {

    @Schema(description = "스터디명", example = "직장인 영어 회화 스터디")
    private String studyName;

    @Schema(description = "글 제목", example = "직장인 영어 회화 스터디 구해요")
    private String title;

    @Schema(description = "스터디 소개", example = "외국인 선생님과 함께하는 직장인 영어 회화 스터디 모집합니다.")
    private String introduction;

    @Schema(description = "활동 내용", example = "5분씩 스피치 및 피드백")
    private String content;

    @Schema(description = "모집 인원", example = "10")
    private int capacity;

    @Schema(description = "정기 모임 여부", example = "true")
    private boolean isRegular;

    @Schema(description = "모집 종료 날짜", example = "2024-08-31")
    private LocalDate recruitEndDate;

    @Schema(description = "시작 날짜", example = "2024-09-01")
    private LocalDate stDate;

    @Schema(description = "종료 날짜", example = "2024-12-31")
    private LocalDate endDate;

    @Schema(description = "작성자 ID")
    private Long writerId;

    @Schema(description = "작성자 닉네임", example = "JOIN해요")
    private String writerNickname;

    @Schema(description = "정기 모임 스케줄")
    private List<StudyScheduleResponse> schedules;

    @Schema(description = "스터디 규칙", example = "지각 시 벌금 1,000원 부과")
    private String ruleExp;

    @Schema(description = "지원 자격", example = "열정 있는 사람이라면 누구나")
    private String qualificationExp;

}
