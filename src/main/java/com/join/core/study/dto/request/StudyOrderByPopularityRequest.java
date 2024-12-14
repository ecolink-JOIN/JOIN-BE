package com.join.core.study.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StudyOrderByPopularityRequest(@NotNull LocalDateTime now) {
}
