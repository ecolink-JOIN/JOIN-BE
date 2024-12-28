package com.join.core.study.dto.request;

import com.join.core.study.constant.StudyForm;

import java.time.LocalDateTime;

public record StudyOrderByPopularityParameter(
        String category,
        StudyForm form,
        LocalDateTime now
) {
}
