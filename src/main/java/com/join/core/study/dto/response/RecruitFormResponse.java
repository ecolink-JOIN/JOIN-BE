package com.join.core.study.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.join.core.rule.dto.request.RuleRequest;
import com.join.core.schedule.dto.request.StudyScheduleRequest;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitFormResponse {
    private int capacity;
    private boolean isRegular;
    private LocalDate recruitEndDate;
    private StudyForm form;
    private LocalDate stDate;
    private LocalDate endDate;
    private String province;
    private String city;
    private String categoryName;
    private String studyName;
    private String title;
    private String introduction;
    private String content;
    private List<RuleRequest> rules;
    private String ruleExp;
    private String qualificationExp;
    private List<StudyScheduleRequest> schedules;

    public static RecruitFormResponse from(Study study) {
        return RecruitFormResponse.builder()
                .capacity(study.getCapacity())
                .isRegular(study.isRegular())
                .recruitEndDate(study.getRecruitEndDate())
                .form(study.getForm())
                .stDate(study.getStDate())
                .endDate(study.getEndDate())
                .province(study.getAddress().getProvince())
                .city(study.getAddress().getCity())
                .categoryName(study.getCategory().getCategoryName())
                .studyName(study.getStudyName())
                .title(study.getTitle())
                .introduction(study.getIntroduction())
                .content(study.getContent())
                .rules(study.getRules().stream()
                        .map(rule -> RuleRequest.of(rule.getType()))
                        .collect(Collectors.toList()))
                .ruleExp(study.getRuleExp())
                .qualificationExp(study.getQualificationExp())
                .schedules(study.getSchedules().stream()
                        .map(schedule -> StudyScheduleRequest.of(schedule.getWeekOfDay(), schedule.getStTime(), schedule.getEndTime()))
                        .collect(Collectors.toList()))
                .build();
    }

}