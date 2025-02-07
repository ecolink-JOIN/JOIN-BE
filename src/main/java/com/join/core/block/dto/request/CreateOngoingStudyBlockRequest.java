package com.join.core.block.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema
public record CreateOngoingStudyBlockRequest(
        @NotNull @Schema(example = "avt_token") String targetAvatarToken,
        @NotNull @Schema(example = "st_token") String studyToken,
        @NotNull @Schema(example = "2025-01-20") LocalDate blockDate
) {
}
