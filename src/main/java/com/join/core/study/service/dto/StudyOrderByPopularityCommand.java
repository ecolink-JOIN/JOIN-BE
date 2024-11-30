package com.join.core.study.service.dto;

import com.join.core.study.constant.StudyForm;

import java.time.LocalDateTime;

public record StudyOrderByPopularityCommand(String categoryName, StudyForm form, LocalDateTime now) {
}
