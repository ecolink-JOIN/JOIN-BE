package com.join.core.study.dto.request;

import com.join.core.study.constant.StudyForm;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema
public record StudyOrderByPopularityParameter(
        @Schema(example = "입시") String category,
        @Schema(example = "ONLINE") StudyForm form,
        @Schema(example = "2025-01-18T10:08:21", type = "string") LocalDateTime now
) {
}
